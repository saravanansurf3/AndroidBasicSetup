package com.sara.waie.androidbasicsetup.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface SafeApiRequest {

    suspend fun <T> safeApi(apiCall: suspend () -> T): NetworkResource<T> {
        return withContext(Dispatchers.IO) {
            try {
                NetworkResource.onSuccess(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        NetworkResource.onFailure(
                            throwable.code(),
                            throwable.response()?.errorBody()
                        )
                    }
                    else -> {
                        NetworkResource.onFailure(-1, null)
                    }
                }
            }
        }
    }
}