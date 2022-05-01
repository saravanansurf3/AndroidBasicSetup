package com.sara.waie.androidbasicsetup.data.localsource.dao

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.*
import com.sara.waie.androidbasicsetup.model.MyFavPost
import com.sara.waie.androidbasicsetup.utils.Constants.TABLE_NAME_MY_FAV

@Dao
interface MyFavPostDao {
    @Query("SELECT * from $TABLE_NAME_MY_FAV")
    fun getAllMyFav():PagingSource<Int,MyFavPost>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToMyFav(posts:List<MyFavPost>)

    @Query("DELETE from $TABLE_NAME_MY_FAV")
    suspend fun deleteAllMyFav()

}