package com.codeinger.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.codeinger.workpulsetask.model.ItemsModel

@Dao
interface ItemsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(itemsModel: ItemsModel)

    @Update
    suspend fun updateItem(itemsModel: ItemsModel)

    @Delete
    suspend fun deleteItem(itemsModel: ItemsModel)

    @Query("DELETE FROM news_table")
    suspend fun deleteAllItems(): Int

    @Query("SELECT * FROM news_table ORDER BY id ASC")
    fun readAllData():  List<ItemsModel>
}