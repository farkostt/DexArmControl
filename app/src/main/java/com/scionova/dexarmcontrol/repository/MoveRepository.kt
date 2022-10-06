package com.scionova.dexarmcontrol.repository

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoveRepository @Inject constructor(private val api: ApiService) {

    fun test(){
        Log.d("debug", "test")
    }

    suspend fun sendNewMove(msg: String) {
        Log.d("debug", "sendNewMove($msg)")
        return api.newMove(msg)
    }

}