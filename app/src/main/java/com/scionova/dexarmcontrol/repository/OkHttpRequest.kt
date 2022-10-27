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
            .add("message", parameter)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .build()

        val call = client.newCall(request)
        call.enqueue(callback)
        return call
    }

    fun STREAM(url: String, callback: Callback): Call {

        val requestBody = object : RequestBody() {
            override fun contentType() = MEDIA_TYPE_MARKDOWN

            override fun writeTo(sink: BufferedSink) {
                sink.writeUtf8("Numbers\n")
                sink.writeUtf8("-------\n")
                for (i in 2..997) {
                    sink.writeUtf8(String.format(" * $i = ${factor(i)}\n"))
                }
            }

            private fun factor(n: Int): String {
                for (i in 2 until n) {
                    val x = n / i
                    if (x * i == n) return "${factor(x)} × $i"
                }
                return n.toString()
            }
        }

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val call = client.newCall(request)
        call.enqueue(callback)

        return call
    }
}