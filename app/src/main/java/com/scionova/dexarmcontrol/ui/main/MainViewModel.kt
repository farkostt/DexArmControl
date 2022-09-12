package com.scionova.dexarmcontrol.ui.main

import android.annotation.SuppressLint
import android.app.Application
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.scionova.dexarmcontrol.R

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

    fun setupVerticalArrowsRight(
        btnArrowUpVertical: ImageView,
        btnArrowDownVertical: ImageView,
        btnArrowMiddleVertical: ImageView
    ) {
        setupTouchListener(btnArrowUpVertical, "Vertical Right Up - Pressed", "Vertical Right Up - Released")
        setupTouchListener(btnArrowDownVertical, "Vertical Right Down - Pressed", "Vertical Right Down - Released")
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