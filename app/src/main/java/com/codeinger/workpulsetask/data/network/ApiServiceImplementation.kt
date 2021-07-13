package com.codeinger.workpulsetask.data.network

import com.codeinger.workpulsetask.model.ItemsModel
import javax.inject.Inject

class ApiServiceImplementation @Inject constructor(private val apiService: ApiService) {

    suspend fun getPosts(s: String): List<Int> =apiService.getPosts("print=pretty")
    suspend fun getSinglePosts(s: String): ItemsModel =apiService.getSinglePosts(s)



}