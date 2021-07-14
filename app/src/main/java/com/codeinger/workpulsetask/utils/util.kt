package com.codeinger.workpulsetask.utils

import android.content.Context
import android.net.ConnectivityManager

object util {

    fun isNetworkAvailable(context: Context) : Boolean{
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            return true
        }
        else {
            return false
        }
    }

}