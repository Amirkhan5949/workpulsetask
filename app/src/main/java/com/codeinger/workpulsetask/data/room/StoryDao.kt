package com.codeinger.workpulsetask.data.room

import androidx.room.*
import com.codeinger.workpulsetask.model.StoryModel

@Dao
interface StoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addStory(storyModel: StoryModel)

    @Update
    suspend fun updateStory(storyModel: StoryModel)

    @Delete
    suspend fun deleteStory(storyModel: StoryModel)

    @Query("DELETE FROM story_table")
    suspend fun deleteAllStories(): Int

    @Query("SELECT * FROM story_table ORDER BY score ASC")
    suspend fun readAllStories():  List<StoryModel>

    @Query("SELECT * FROM story_table WHERE id = :id")
    suspend fun getStoryById(id : Int):  StoryModel?
}