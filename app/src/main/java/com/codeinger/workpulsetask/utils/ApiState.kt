package com.codeinger.workpulsetask.utils

import com.codeinger.workpulsetask.model.StoryModel


sealed class ApiState {

    object Loading : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    class Success(val data: List<StoryModel>) : ApiState()
    object Empty : ApiState()

}

