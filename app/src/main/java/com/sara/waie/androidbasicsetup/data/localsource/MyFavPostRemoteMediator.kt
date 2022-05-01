package com.sara.waie.androidbasicsetup.data.localsource

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.sara.waie.androidbasicsetup.data.localsource.dao.MyFavPostRemoteKeyDao
import com.sara.waie.androidbasicsetup.model.MyFavPost
import com.sara.waie.androidbasicsetup.model.MyFavPostRemoteKey
import com.sara.waie.androidbasicsetup.network.Webservice
import java.lang.Exception
private const val TAG="MyFavPostRemoteMediator"
@ExperimentalPagingApi
class MyFavPostRemoteMediator(
    private val webservice: Webservice,
    private val database: MyDatabase
) : RemoteMediator<Int, MyFavPost>() {
    private val myFavPostDao = database.myFavDao()
    private val myFavPostRemoteKeyDao = database.myFavPostRemoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MyFavPost>
    ): MediatorResult {

        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    Log.d(TAG,"load LoadType.REFRESH")
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1

                }
                LoadType.APPEND -> {
                    Log.d(TAG,"load LoadType.APPEND")
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKey?.nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                    nextPage

                }
                LoadType.PREPEND -> {
                    Log.d(TAG,"load LoadType.PREPEND")
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKey?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKey != null
                    )
                    prevPage
                }
            }
            Log.d(TAG,"finally currentPage"+currentPage)
            val response = webservice.getMyFavPost(currentPage)
            val isEndOfPage = response.data.myFave.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (isEndOfPage) null else currentPage + 1

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    Log.d(TAG,"sorry need to clear local")
                    myFavPostDao.deleteAllMyFav()
                    myFavPostRemoteKeyDao.deleteAllMyRemoteKeys()
                    Log.d(TAG,"sorry need to clear local-> Done")
                }
                val keys = response.data.myFave.map { post ->
                    MyFavPostRemoteKey(
                        id = post.itemId,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                myFavPostRemoteKeyDao.addAllRemoteKeys(keys)
                myFavPostDao.addToMyFav(posts = response.data.myFave)
            }
            Log.d(TAG,"isEndOfPage "+isEndOfPage)
            MediatorResult.Success(endOfPaginationReached = isEndOfPage)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }


    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, MyFavPost>
    ): MyFavPostRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.itemId?.let { itemId ->
                myFavPostRemoteKeyDao.getRemoteKey(postId = itemId)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, MyFavPost>
    ): MyFavPostRemoteKey? {

        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                myFavPostRemoteKeyDao.getRemoteKey(postId = unsplashImage.itemId)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, MyFavPost>
    ): MyFavPostRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                myFavPostRemoteKeyDao.getRemoteKey(postId = unsplashImage.itemId)
            }
    }
}