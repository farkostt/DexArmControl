package com.scionova.dexarmcontrol.ui.main

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel

class MainViewModel(application: Application) : AndroidViewModel(application){

    private val context = getApplication<Application>().applicationContext

    fun setupSquareArrows(
        btnArrowUp: ImageView,
        btnArrowRight: ImageView,
        btnArrowDown: ImageView,
        btnArrowLeft: ImageView,
        btnArrowMiddle: ImageView
    ) {

        setupTouchListener(btnArrowUp, "Up - Pressed", "Up - Released")
        setupTouchListener(btnArrowRight, "Right - Pressed", "Right - Released")
        setupTouchListener(btnArrowDown, "Down - Pressed", "Down - Released")
        setupTouchListener(btnArrowLeft, "Left - Pressed", "Left - Released")
        setupTouchListener(btnArrowMiddle, "Middle - Pressed", "Middle - Released")

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

    fun setupVerticalArrows(
        btnArrowUpVertical: Button,
        btnArrowDownVertical: Button,
        btnArrowMiddleVertical: Button
    ) {



    }

}