package com.sara.waie.androidbasicsetup.network

import okhttp3.ResponseBody

sealed class NetworkResource<out T>(val errorCode: Int, val errorMsg: String, val data: T?) {



    data class onSuccess<out T>(val response:T):NetworkResource<T>(0,"",response)
    data class onFailure<out T>(val code: Int,
                                val errorBody: ResponseBody?):NetworkResource<T>(code,errorBody.toString(),null)
    object onLoading:NetworkResource<Nothing>(0,"Loading",null)
}