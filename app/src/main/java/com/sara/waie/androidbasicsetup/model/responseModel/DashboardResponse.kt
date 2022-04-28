package com.sara.waie.androidbasicsetup.model.responseModel

import com.google.gson.annotations.SerializedName
import com.sara.waie.androidbasicsetup.model.Post

data class DashboardResponse(val baseResponse: BaseResponse,
                             val data:DashboardResponseData){

}




data class DashboardResponseData(
    @SerializedName("todaySale")
val todaySales: ArrayList<Post>,
    @SerializedName("PopularSale")
    val popularSale: ArrayList<Post>,
    @SerializedName("suggestion")
    val suggestion: ArrayList<Post>
){


}