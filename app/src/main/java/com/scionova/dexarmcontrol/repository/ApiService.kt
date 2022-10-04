package com.scionova.dexarmcontrol.repository

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {

    @POST("/move/x/{value}")
    suspend fun moveAxisX(@Path("value") x: Int)

}