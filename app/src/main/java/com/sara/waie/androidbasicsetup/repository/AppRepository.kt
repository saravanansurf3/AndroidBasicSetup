package com.sara.waie.androidbasicsetup.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sara.waie.androidbasicsetup.model.Order
import com.sara.waie.androidbasicsetup.model.responseModel.DashboardResponse
import com.sara.waie.androidbasicsetup.network.NetworkResource
import com.sara.waie.androidbasicsetup.network.SafeApiRequest
import com.sara.waie.androidbasicsetup.network.Webservice
import com.sara.waie.androidbasicsetup.repository.datasource.RecentOrdersDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class AppRepository @Inject constructor(private val webservice: Webservice) : SafeApiRequest {

    suspend fun getDashboard(): NetworkResource<DashboardResponse> {
        return safeApi { webservice.getDashboard() }
    }

    fun getMyOrderHistory(): Flow<PagingData<Order>> {
        return Pager(config = PagingConfig(25),
            pagingSourceFactory = { RecentOrdersDataSource(webservice) }).flow
    }

}