package com.sara.waie.androidbasicsetup.network

import com.sara.waie.androidbasicsetup.model.Post
import com.sara.waie.androidbasicsetup.model.responseModel.DashboardResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Webservice {


    @GET("/dashboard")
    suspend fun getDashboard():DashboardResponse

}