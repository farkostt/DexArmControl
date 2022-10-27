package com.scionova.dexarmcontrol.repository

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okio.BufferedSink
import okio.IOException
import java.io.OutputStream

class OkHttpRequest(client: OkHttpClient) {
    private var client = OkHttpClient()

    init {
        this.client = client
    }

    companion object {
        val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaType()
    }

    fun GET(url: String, callback: Callback): Call {
        val request = Request.Builder()
            .url(url)
            .build()

        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }

    fun POST(url: String, parameter: String, callback: Callback): Call {
        val formBody = FormBody.Builder()
            .add("command", parameter)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }
}