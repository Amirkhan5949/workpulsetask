package com.codeinger.workpulsetask.repository

import com.codeinger.workpulsetask.data.network.ApiServiceImplementation
import com.codeinger.workpulsetask.data.room.ItemsDao
import com.codeinger.workpulsetask.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ItemsRepository @Inject constructor (private val apiServiceImplementation: ApiServiceImplementation,
private val itemsDao: ItemsDao){

    fun getPosts(s: String): Flow <List<Int>> =
        flow {
            emit(apiServiceImplementation.getPosts("print=pretty"))
        }.flowOn(Dispatchers.IO)

    suspend fun getSinglePosts(s: String): ItemsModel =
        apiServiceImplementation.getSinglePosts(s)



    suspend fun readAllData() : List<ItemsModel>{
        return itemsDao.readAllData()
    }


    suspend fun getItemById(id : Int) : ItemsModel?{
        return itemsDao.getItemById(id)
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