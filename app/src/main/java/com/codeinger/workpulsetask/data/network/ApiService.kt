package com.codeinger.workpulsetask.data.network

import com.codeinger.workpulsetask.model.ItemsModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("topstories.json?print=pretty")
    suspend fun getPosts(@Query("print=pretty") print : String) : List<Int>


    @GET("item/{id}")
    suspend fun getSinglePosts(@Path ("id") id : String)  : ItemsModel


}