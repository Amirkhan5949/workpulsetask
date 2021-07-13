package com.codeinger.repository

import androidx.lifecycle.LiveData
import com.codeinger.network.ApiServiceImplementation
import com.codeinger.room.ItemsDao
import com.codeinger.workpulsetask.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ItemsRepository @Inject constructor (private val apiServiceImplementation: ApiServiceImplementation,
private val itemsDao: ItemsDao){

    fun getPosts() : Flow <List<ItemsModel>> =
        flow {
            emit(apiServiceImplementation.getPosts())
        }.flowOn(Dispatchers.IO)


    suspend fun getPostFromRoom() : List<ItemsModel>{
        return itemsDao.readAllData()
    }




    suspend fun addItem(itemsModel: ItemsModel){
        itemsDao.addItem(itemsModel)
    }

    suspend fun updateItem(itemsModel: ItemsModel){
        itemsDao.updateItem(itemsModel)
    }

    suspend fun deleteItem(itemsModel: ItemsModel){
        itemsDao.deleteItem(itemsModel)
    }

    suspend fun deleteAllItems(){
        itemsDao.deleteAllItems()
    }
}