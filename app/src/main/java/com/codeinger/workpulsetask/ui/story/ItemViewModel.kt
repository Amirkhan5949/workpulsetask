package com.codeinger.workpulsetask.ui.story

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeinger.workpulsetask.data.room.ItemsDao
import com.codeinger.workpulsetask.model.ItemsModel
import com.codeinger.workpulsetask.repository.ItemsRepository
import com.codeinger.workpulsetask.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.stream.IntStream.range
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val itemsRepository: ItemsRepository
) : ViewModel() {

    private val mutablePosts: MutableLiveData<ApiState> = MutableLiveData()
    val postsLiveData: LiveData<ApiState> = mutablePosts

    val topStoriesId = arrayListOf<Int>()


    val itemList = arrayListOf<ItemsModel>()

    init {
        viewModelScope.launch (Dispatchers.IO){
            val data = itemsRepository.readAllData()
            if(data!=null&&data.isNotEmpty()){
                itemList.addAll(data)
                withContext(Dispatchers.Main) {
                    mutablePosts.value = ApiState.Success(itemList)
                }
            }
        }
    }



    fun getStorys() = viewModelScope.launch(Dispatchers.IO) {

        withContext(Dispatchers.Main) {
            mutablePosts.value = ApiState.Loading
        }
        itemsRepository.getPosts("print=pretty").catch { e ->

        }.collect { ids ->
            for(x in 0..49){
                var data = itemsRepository.getItemById(ids[x])
                Log.i("dscbsm", "data: "+data)
                if(data==null){
                    Log.i("smadnxm", "async: "+data)
                    data = async { itemsRepository.getSinglePosts("${ids.get(x)}.json") }.await()
                    Log.i("dscbsm", "async: "+data)
                    itemsRepository.addItem(data)
                }
                itemList.add(data)
            }
            Log.i("smadnxm", "outside : "+ids)
            withContext(Dispatchers.Main) {
                mutablePosts.value = ApiState.Success(itemList)
            }
        }

    }




//    fun getSinglePost(query: String) = viewModelScope.launch {
//        mutablePosts.value = ApiState.Loading
//        itemsRepository.getSinglePosts("8863.json")
//            .catch { e ->
//                mutablePosts.value = ApiState.Failure(e)
//            }.collect { data ->
//                mutablePosts.value = ApiState.Success(data)
//            }
//    }





}
