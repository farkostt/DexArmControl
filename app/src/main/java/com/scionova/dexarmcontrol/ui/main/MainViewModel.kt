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
import androidx.lifecycle.viewModelScope
import com.scionova.dexarmcontrol.R
import com.scionova.dexarmcontrol.repository.Move
import com.scionova.dexarmcontrol.repository.MoveRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val moveRepository: MoveRepository) : ViewModel(){

    fun setupSquareArrows(
        btnArrowUp: ImageView,
        btnArrowRight: ImageView,
        btnArrowDown: ImageView,
        btnArrowLeft: ImageView,
        btnArrowMiddle: ImageView
    ) {
        setupNewTouchListener(btnArrowUp, "G0 X10", "Up - Released")
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
    private fun setupNewTouchListener(button: ImageView, msgPressed: String, msgReleased: String){
        button.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action){
                MotionEvent.ACTION_DOWN -> {
                    Log.d("Controls", msgPressed)
                    viewModelScope.launch {
                        try {
                            // Calling the repository is safe as it will move execution off
                            // the main thread
                            Log.d("viewModelScope", "sending http...")
                            val response = moveRepository.sendMoveAxisX(msgPressed.toInt())
                            Log.d("debug", response.toString())
                        } catch (error: Exception) {
                            // show error message to user
                        }

                    }
                }
                MotionEvent.ACTION_UP -> {
                    Log.d("Controls", msgReleased)
                }
            }

            true
        }
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