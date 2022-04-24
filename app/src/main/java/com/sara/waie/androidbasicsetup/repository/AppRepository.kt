package com.sara.waie.androidbasicsetup.repository

import com.sara.waie.androidbasicsetup.model.Post
import com.sara.waie.androidbasicsetup.model.responseModel.DashboardResponse
import com.sara.waie.androidbasicsetup.network.NetworkResource
import com.sara.waie.androidbasicsetup.network.SafeApiRequest
import com.sara.waie.androidbasicsetup.network.Webservice
import javax.inject.Inject


class AppRepository @Inject constructor(private val webservice: Webservice) :SafeApiRequest{

    suspend fun getDashboard():NetworkResource<DashboardResponse>{
        return safeApi {  webservice.getDashboard()}
    }
}