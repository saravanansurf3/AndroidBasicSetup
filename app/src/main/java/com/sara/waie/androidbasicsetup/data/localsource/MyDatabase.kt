package com.sara.waie.androidbasicsetup.data.localsource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sara.waie.androidbasicsetup.data.localsource.dao.MyFavPostDao
import com.sara.waie.androidbasicsetup.data.localsource.dao.MyFavPostRemoteKeyDao
import com.sara.waie.androidbasicsetup.model.MyFavPost
import com.sara.waie.androidbasicsetup.model.MyFavPostRemoteKey

@Database(entities = [MyFavPost::class,MyFavPostRemoteKey::class],version = 1)
abstract class MyDatabase: RoomDatabase() {

    abstract fun myFavDao():MyFavPostDao
    abstract fun myFavPostRemoteKeyDao(): MyFavPostRemoteKeyDao

}