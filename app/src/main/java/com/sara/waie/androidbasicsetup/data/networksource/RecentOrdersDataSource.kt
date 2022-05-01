package com.sara.waie.androidbasicsetup.data.networksource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sara.waie.androidbasicsetup.model.Order
import com.sara.waie.androidbasicsetup.network.Webservice
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val STARTING_PAGE_NUM = 1

class RecentOrdersDataSource @Inject constructor(private val webservice: Webservice) :
    PagingSource<Int, Order>() {
    override fun getRefreshKey(state: PagingState<Int, Order>): Int? {

        return state.anchorPosition?.let { currentCursorPos ->
            state.closestPageToPosition(currentCursorPos)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(currentCursorPos)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Order> {
        val pageNum = params.key ?: STARTING_PAGE_NUM
        return try {
            val response = webservice.getRecentOrderHistory(pageNum)

           val  prvKey=if (pageNum==1){
                null
            }else{
               pageNum-1
            }
            val nextKey = if (response.data.orderHistory.isNotEmpty()) {
                pageNum + 1
            } else {
                null
            }


            LoadResult.Page(
                data = response.data.orderHistory,
                prevKey = prvKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }


}