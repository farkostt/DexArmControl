package com.scionova.dexarmcontrol.ui.main

import android.annotation.SuppressLint
import android.util.Log
import android.view.MotionEvent
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.scionova.dexarmcontrol.repository.OkHttpRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okio.IOException
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject


class MainViewModel : ViewModel(){

    private val client = OkHttpClient()
    private val request = OkHttpRequest(client)


    fun setupSquareArrows(
        btnArrowForward: ImageView,
        btnArrowRight: ImageView,
        btnArrowBackward: ImageView,
        btnArrowLeft: ImageView,
        btnArrowMiddle: ImageView
    ) {
        setupNewTouchListener(btnArrowForward, "G0 X10", "Forward - Released")
        setupNewTouchListener(btnArrowRight, "G0 Z10", "Right - Released")
        setupNewTouchListener(btnArrowBackward, "G0 X-10", "Backward - Released")
        setupNewTouchListener(btnArrowLeft, "G0 Z-10", "Left - Released")
        setupTouchListener(btnArrowMiddle, "Middle - Pressed", "Middle - Released")
    }

    fun setupVerticalArrowsRight(
        btnArrowUpVertical: ImageView,
        btnArrowDownVertical: ImageView,
        btnArrowMiddleVertical: ImageView
    ) {
        setupNewTouchListener(btnArrowUpVertical, "G0 Y10", "Vertical Right Up - Released")
        setupNewTouchListener(btnArrowDownVertical, "G0 Y-10", "Vertical Right Down - Released")
        setupTouchListener(btnArrowMiddleVertical, "Vertical Right Middle - Pressed", "Vertical Right Middle - Released")
    }

    fun setupVerticalArrowsLeft(
        btnArrowUpVertical: ImageView,
        btnArrowDownVertical: ImageView,
        btnArrowMiddleVertical: ImageView
    ) {
        setupTouchListener(btnArrowUpVertical, "Vertical Left Up - Pressed", "Vertical Left Up - Released")
        setupTouchListener(btnArrowDownVertical, "Vertical Left Down - Pressed", "Vertical Left Down - Released")
        setupTouchListener(btnArrowMiddleVertical, "Vertical Left Middle - Pressed", "Vertical Left Middle - Released")
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupNewTouchListener(button: ImageView, msgPressed: String, msgReleased: String){
        button.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_UP -> {
                    // On release off button, send stop request.
                    viewModelScope.launch {
                        val url = "http://10.0.2.2:50505"
                        request.POST(url, "stop", object: Callback {
                            override fun onFailure(call: Call, e: java.io.IOException) {
                                Log.d("Failed to send stop", "failed: $e")
                            }

                            override fun onResponse(call: Call, response: Response) {
                                val responseData = response.body?.string()
                                try {
                                    var json = JSONObject(responseData)
                                    this@MainViewModel.fetchComplete()
                                } catch (e: JSONException) {
                                    e.printStackTrace()
                                }
                            }
                        })
                    }
                }
                MotionEvent.ACTION_DOWN -> {
                    Log.d("Controls", msgPressed)
                    viewModelScope.launch {
                        val url = "http://10.0.2.2:50505"

                        request.POST(url, msgPressed, object: Callback {

                            override fun onFailure(call: Call, e: java.io.IOException) {
                                Log.d("OkHttpRequest POST", "failed: $e")
                            }

                            override fun onResponse(call: Call, response: Response) {
                                val responseData = response.body?.string()
                                try {
                                    var json = JSONObject(responseData)
                                    println("Request Successful!!")
                                    this@MainViewModel.fetchComplete()
                                } catch (e: JSONException) {
                                    e.printStackTrace()
                                }
                            }
                        })

                    }
                }
            }

            true
        }
    }

    private fun fetchComplete() {
        Log.d("MainViewModel", "fetch complete")
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupTouchListener(button: ImageView, msgPressed: String, msgReleased: String){
        button.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> {
                    Log.d("Controls", msgPressed)


                }
                MotionEvent.ACTION_UP -> {
                    Log.d("Controls", msgReleased)
                }
            }

            true
        }
    }

}