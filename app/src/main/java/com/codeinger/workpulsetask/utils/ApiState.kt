package com.codeinger.workpulsetask.utils

import com.codeinger.workpulsetask.model.ItemsModel


sealed class ApiState {

    object Loading : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    class Success(val data: List<ItemsModel>) : ApiState()
    object Empty : ApiState()

}

