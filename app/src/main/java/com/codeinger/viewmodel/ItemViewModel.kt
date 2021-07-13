package com.codeinger.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.codeinger.repository.ItemsRepository
import com.codeinger.room.ItemDatabase
import com.codeinger.utils.ApiState
import com.codeinger.workpulsetask.model.ItemsModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class ItemViewModel @Inject constructor(private val itemsRepository: ItemsRepository,application: Application) : AndroidViewModel(application){

    private val mutablePosts : MutableLiveData<ApiState> = MutableLiveData()
    val postsLiveData : LiveData<ApiState> = mutablePosts

    init {

        val itemsDao = ItemDatabase.getDatabase(application).itemDao()

    }


    fun getPostFromRoom(){
        viewModelScope.launch{
                mutablePosts.value=ApiState.Success(itemsRepository.getPostFromRoom())
        }
    }

     fun getPost() = viewModelScope.launch {
         mutablePosts.value =ApiState.Loading
         itemsRepository.getPosts()
             .catch {e->
                 mutablePosts.value=ApiState.Failure(e)
             }.collect { data->
                 mutablePosts.value=ApiState.Success(data)
             }
     }

    fun addI(itemsModel: ItemsModel){
        viewModelScope.launch(Dispatchers.IO) {
            itemsRepository.addItem(itemsModel)
        }
    }

    fun updateI(itemsModel: ItemsModel){
        viewModelScope.launch(Dispatchers.IO) {
            itemsRepository.updateItem(itemsModel)
        }
    }

    fun deleteI(itemsModel: ItemsModel){
        viewModelScope.launch(Dispatchers.IO) {
            itemsRepository.deleteItem(itemsModel)
        }
    }

    fun deleteAllIs(){
        viewModelScope.launch(Dispatchers.IO) {
            itemsRepository.deleteAllItems()
        }
    }



}
