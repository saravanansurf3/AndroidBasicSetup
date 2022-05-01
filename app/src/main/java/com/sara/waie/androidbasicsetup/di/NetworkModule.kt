package com.sara.waie.androidbasicsetup.di

import com.sara.waie.androidbasicsetup.network.Webservice
import dagger.Component
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

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }



    @Provides
    @Singleton
    fun provideOkHttp(
        loggingInterceptor123: HttpLoggingInterceptor,
    ): okhttp3.Call.Factory  {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor123)
            .callTimeout(600, TimeUnit.SECONDS)
            .readTimeout(600, TimeUnit.SECONDS)
            .connectTimeout(10000, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideWebService(
        callFactory: okhttp3.Call.Factory
    ): Webservice = Retrofit.Builder()
        .baseUrl("https://d1269b4fac.to.intercept.rest")
        .callFactory(callFactory)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(Webservice::class.java)

    @Provides
    @Singleton
    @Named("auth_token")
    fun providesAuthToken():String{
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }
}