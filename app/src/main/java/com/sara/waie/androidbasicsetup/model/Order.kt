package com.sara.waie.androidbasicsetup.model

import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("orderId")
    val orderId: String,
    @SerializedName("itemId")
    val itemId: String,
    @SerializedName("item_titile")
    val itemName: String,
    @SerializedName("item_desc")
    val itemDesc: String,
    @SerializedName("item_photo")
    val itemPhoto: String,
    @SerializedName("price_per_unint")
    val pricePerUnit: Float,
    @SerializedName("currency")
    val currecny: String,
    @SerializedName("num_of_unit")
    val numberOfUnit: Int,
    @SerializedName("total_amount")
    val totalAmount: Float,
    @SerializedName("total_discount")
    val totalDiscount: Float,
    @SerializedName("net_amount")
    val netAmount: Float
)
