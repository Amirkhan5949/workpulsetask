package com.codeinger.workpulsetask.di

import android.content.Context
import com.codeinger.workpulsetask.data.network.ApiService
import com.codeinger.workpulsetask.data.room.StoryDatabase
import com.codeinger.workpulsetask.data.room.StoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
class AppModule {

    @Provides
    fun provideBaseUrl()= "https://hacker-news.firebaseio.com/v0/"

    @Provides
    @Singleton
    fun getStoryDao(storyDataBase: StoryDatabase):StoryDao = storyDataBase.storyDao()

    @Provides
    @Singleton
    fun getMyRoomDataBase(@ApplicationContext appContext: Context) = StoryDatabase.getDatabase(appContext)


    @Provides
    @Singleton
    fun provideApiService(url : String) : ApiService =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}