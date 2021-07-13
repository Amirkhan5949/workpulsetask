package com.codeinger.network

import com.codeinger.workpulsetask.model.ItemsModel
import javax.inject.Inject

class ApiServiceImplementation @Inject constructor(private val apiService: ApiService) {

    suspend fun getPosts(): List<ItemsModel> =apiService.getPosts()

}