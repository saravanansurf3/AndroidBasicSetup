package com.sara.waie.androidbasicsetup.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sara.waie.androidbasicsetup.utils.Constants.TABLE_NAME_MY_FAV

@Entity(tableName = TABLE_NAME_MY_FAV)
data class MyFavPost(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val itemId: String,
    @ColumnInfo(name = "name")
    val titile: String,
    @ColumnInfo(name = "desc")
    val desc: String,
    @ColumnInfo(name = "photo")
    val photo:String,
    @ColumnInfo(name = "price")
    val price: Float,
    @ColumnInfo(name = "currency")
    val currency: String,
    @ColumnInfo(name = "stockInAvailable")
    val stockInAvailable: String
)
