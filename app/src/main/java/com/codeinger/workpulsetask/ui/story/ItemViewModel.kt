package com.codeinger.workpulsetask.ui.story

import androidx.lifecycle.*
import com.codeinger.workpulsetask.model.ItemsModel
import com.codeinger.workpulsetask.repository.ItemsRepository
import com.codeinger.workpulsetask.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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
        viewModelScope.launch  {
           val data = withContext(Dispatchers.IO){
               itemsRepository.readAllData()
           }
            if (data != null && data.isNotEmpty()) {
                itemList.addAll(data)
                mutablePosts.value = ApiState.Success(itemList)
            }
        }
    }

    fun getStorys() {

        mutablePosts.value = ApiState.Loading
        viewModelScope.launch(Dispatchers.IO) {

            itemsRepository.getPosts("print=pretty").catch { e ->
            }.collect { ids ->
                itemList.clear()
                coroutineScope {
                    for (x in 0..49) {
                        var data = itemsRepository.getItemById(ids[x])
                        if (data == null) {
                            launch {
                                val apiResponse =
                                    itemsRepository.getSinglePosts("${ids.get(x)}.json") //suspend function
                                itemsRepository.addItem(apiResponse)
                                itemList.add(apiResponse)
                            }
                        }
                        else{
                            itemList.add(data!!)
                        }

                    }
                }
                var list = itemList.sortedWith(compareBy({ it.score }))
                withContext(Dispatchers.Main) {
                    mutablePosts.value = ApiState.Success(list)
                }
            }

        }
    }


}
