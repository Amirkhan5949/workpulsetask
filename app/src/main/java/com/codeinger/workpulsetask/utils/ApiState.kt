package com.codeinger.workpulsetask.utils

import com.codeinger.workpulsetask.model.ItemsModel


sealed class ApiState {

    object Loading : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    class Success(val data: ArrayList<ItemsModel>) : ApiState()
    object Empty : ApiState()

}

