package com.scionova.dexarmcontrol.repository

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface ApiService {

    @POST("{msg}")
    suspend fun newMove(@Path("msg") msg: String)

}