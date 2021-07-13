package com.codeinger.network

import com.codeinger.workpulsetask.model.ItemsModel
import retrofit2.http.GET

interface ApiService {

    @GET("/item")
    suspend fun getPosts() : List<ItemsModel>
}