package com.sara.waie.androidbasicsetup.model.responseModel

import com.sara.waie.androidbasicsetup.model.MyFavPost

data class MyFavPostListResponse(
    val baseResponse: BaseResponse,
val data:MyFavPostListResponseData
)

data class MyFavPostListResponseData(
    val myFave:ArrayList<MyFavPost>
)
