package com.sara.waie.androidbasicsetup.di

import android.content.Context
import androidx.room.Room
import com.sara.waie.androidbasicsetup.data.localsource.MyDatabase
import com.sara.waie.androidbasicsetup.utils.Constants.MY_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext appContext: Context): MyDatabase {
        return Room.databaseBuilder(appContext, MyDatabase::class.java, MY_DATABASE_NAME).build()
    }
}