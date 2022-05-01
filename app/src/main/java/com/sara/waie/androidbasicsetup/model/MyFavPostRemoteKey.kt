package com.sara.waie.androidbasicsetup.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sara.waie.androidbasicsetup.utils.Constants.TABLE_NAME_MY_FAV_REMOTE_KEYS

@Entity(tableName = TABLE_NAME_MY_FAV_REMOTE_KEYS)
data class MyFavPostRemoteKey(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name ="myFavPostId")
    val id:String,
    val prevPage:Int?,
    val nextPage:Int?

)
