package com.codeinger.workpulsetask.repository

import com.codeinger.workpulsetask.data.network.ApiServiceImplementation
import com.codeinger.workpulsetask.data.room.StoryDao
import com.codeinger.workpulsetask.model.StoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class StoryRepository @Inject constructor (private val apiServiceImplementation: ApiServiceImplementation,
                                           private val storyDao: StoryDao){

    fun getPosts(s: String): Flow <List<Int>> =
        flow {
            emit(apiServiceImplementation.getPosts("print=pretty"))
        }.flowOn(Dispatchers.IO)

    suspend fun getSinglePosts(s: String): StoryModel =
        apiServiceImplementation.getSinglePosts(s)



    suspend fun readAllStory() : List<StoryModel>{
        return storyDao.readAllStories()
    }


    suspend fun getStoryById(id : Int) : StoryModel?{
        return storyDao.getStoryById(id)
    }

    suspend fun addStory(storyModel: StoryModel){
        storyDao.addStory(storyModel)
    }

    suspend fun updateStory(storyModel: StoryModel){
        storyDao.updateStory(storyModel)
    }

    suspend fun deleteStory(storyModel: StoryModel){
        storyDao.deleteStory(storyModel)
    }

    suspend fun deleteAllStorys(){
        storyDao.deleteAllStories()
    }
}