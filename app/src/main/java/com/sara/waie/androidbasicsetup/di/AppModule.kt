package com.sara.waie.androidbasicsetup.di

import androidx.paging.ExperimentalPagingApi
import com.sara.waie.androidbasicsetup.network.Webservice
import com.sara.waie.androidbasicsetup.data.AppRepository
import com.sara.waie.androidbasicsetup.data.localsource.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@ExperimentalPagingApi
@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesAppRepository(webservice: Webservice,database: MyDatabase):AppRepository{
        return AppRepository(webservice,database)
    }


}

