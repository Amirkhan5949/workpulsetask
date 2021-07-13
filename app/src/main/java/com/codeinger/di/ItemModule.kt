package com.codeinger.di

import com.codeinger.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
class ItemModule {

    @Provides
    fun provideBaseUrl()= "https://hacker-news.firebaseio.com/v0/"

    @Provides
    @Singleton
    fun provideApiService(url : String) : ApiService =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}