package com.sara.waie.androidbasicsetup.model.responseModel

import com.sara.waie.androidbasicsetup.model.Order

data class RecentHistoryResponse(val baseResponse: BaseResponse,val data:RecentHistoryResponseData)

data class RecentHistoryResponseData(val orderHistory:ArrayList<Order>)