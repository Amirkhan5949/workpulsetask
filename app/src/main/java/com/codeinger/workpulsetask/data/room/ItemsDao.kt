package com.codeinger.workpulsetask.data.room

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

    @Query("SELECT * FROM news_table WHERE id = :id")
    fun getItemById(id : Int):  ItemsModel?
}