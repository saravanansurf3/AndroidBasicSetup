package com.sara.waie.androidbasicsetup.data.localsource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sara.waie.androidbasicsetup.model.MyFavPostRemoteKey
import com.sara.waie.androidbasicsetup.utils.Constants.TABLE_NAME_MY_FAV_REMOTE_KEYS

@Dao
interface MyFavPostRemoteKeyDao {
    @Query("SELECT * from $TABLE_NAME_MY_FAV_REMOTE_KEYS where myFavPostId=:postId")
    suspend fun getRemoteKey(postId:String):MyFavPostRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(keyList:List<MyFavPostRemoteKey>)

    @Query("DELETE from $TABLE_NAME_MY_FAV_REMOTE_KEYS")
    suspend fun deleteAllMyRemoteKeys()
}