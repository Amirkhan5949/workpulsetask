package com.codeinger.workpulsetask.ui.story

import androidx.lifecycle.*
import com.codeinger.workpulsetask.model.StoryModel
import com.codeinger.workpulsetask.repository.StoryRepository
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
class StoryViewModel @Inject constructor(
    private val storyRepository: StoryRepository
) : ViewModel() {

    private val mutablePosts: MutableLiveData<ApiState> = MutableLiveData()
    val postsLiveData: LiveData<ApiState> = mutablePosts

    val topStoriesId = arrayListOf<Int>()


    val storyList = arrayListOf<StoryModel>()

    init {
        viewModelScope.launch  {
           val data = withContext(Dispatchers.IO){
               storyRepository.readAllStory()
           }
            if (data != null && data.isNotEmpty()) {
                storyList.addAll(data)
                mutablePosts.value = ApiState.Success(storyList)
            }
        }
    }

    fun getStorys() {


        mutablePosts.value = ApiState.Loading
        viewModelScope.launch(Dispatchers.IO) {

            storyRepository.getPosts("print=pretty").catch { e ->
            }.collect { ids ->
                storyList.clear()
                coroutineScope {
                    for (x in 0..49) {
                        var data = storyRepository.getStoryById(ids[x])
                        if (data == null) {
                            launch {
                                val apiResponse =
                                    storyRepository.getSinglePosts("${ids.get(x)}.json") //suspend function
                                storyRepository.addStory(apiResponse)
                                storyList.add(apiResponse)
                            }
                        }
                        else{
                            storyList.add(data!!)
                        }

                    }
                }
                var list = storyList.sortedWith(compareBy({ it.score }))
                withContext(Dispatchers.Main) {
                    mutablePosts.value = ApiState.Success(list)
                }
            }

        }
    }


}
