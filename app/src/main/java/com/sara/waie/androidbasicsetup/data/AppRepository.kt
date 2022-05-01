package com.sara.waie.androidbasicsetup.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sara.waie.androidbasicsetup.data.localsource.MyDatabase
import com.sara.waie.androidbasicsetup.data.localsource.MyFavPostRemoteMediator
import com.sara.waie.androidbasicsetup.model.Order
import com.sara.waie.androidbasicsetup.model.responseModel.DashboardResponse
import com.sara.waie.androidbasicsetup.network.NetworkResource
import com.sara.waie.androidbasicsetup.network.SafeApiRequest
import com.sara.waie.androidbasicsetup.network.Webservice
import com.sara.waie.androidbasicsetup.data.networksource.RecentOrdersDataSource
import com.sara.waie.androidbasicsetup.model.MyFavPost
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@ExperimentalPagingApi
class AppRepository @Inject constructor(private val webservice: Webservice, private val database: MyDatabase) : SafeApiRequest {

    suspend fun getDashboard(): NetworkResource<DashboardResponse> {
        return safeApi { webservice.getDashboard() }
    }

    fun getMyOrderHistory(): Flow<PagingData<Order>> {
        return Pager(config = PagingConfig(25),
            pagingSourceFactory = { RecentOrdersDataSource(webservice) }).flow
    }

    fun getMyFavPost():Flow<PagingData<MyFavPost>>{
        val pagingSourceFacoty={database.myFavDao().getAllMyFav()}
        return Pager(
            config = PagingConfig(10),
            remoteMediator = MyFavPostRemoteMediator(webservice,database),
            pagingSourceFactory = pagingSourceFacoty
        ).flow

    }

}