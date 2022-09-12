package com.scionova.dexarmcontrol.ui.main

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scionova.dexarmcontrol.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel


        viewModel.setupSquareArrows(binding.btnArrowUp, binding.btnArrowRight, binding.btnArrowDown, binding.btnArrowLeft, binding.btnArrowMiddle)
        viewModel.setupVerticalArrows(binding.btnArrowUpVertical, binding.btnArrowDownVertical, binding.btnArrowMiddleVertical)
    }


    // Not used
    private fun OldInitJoystick(view: ImageView){

        view.setOnLongClickListener { v: View ->
            Log.d("Debug", "click")

            val item = ClipData.Item((v.tag as? CharSequence))
            val dragData = ClipData(
                v.tag as? CharSequence,
                arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
                item)
            val shadow = MyDragShadowBuilder(view)

            v.startDragAndDrop(dragData, shadow, null, 0)

            true
        }

        view.setOnDragListener { v, e ->
            Log.d("Debug", "drag")

            // Handles each of the expected events.
            when (e.action) {
                DragEvent.ACTION_DRAG_STARTED -> {
                    // Determines if this View can accept the dragged data.
                    if (e.clipDescription.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                        // As an example of what your application might do, applies a blue color tint
                        // to the View to indicate that it can accept data.
                        (v as? ImageView)?.setColorFilter(Color.BLUE)

                        // Invalidate the view to force a redraw in the new tint.
                        v.invalidate()

                        // Returns true to indicate that the View can accept the dragged data.
                        true
                    } else {
                        // Returns false to indicate that, during the current drag and drop operation,
                        // this View will not receive events again until ACTION_DRAG_ENDED is sent.
                        false
                    }
                }
                DragEvent.ACTION_DRAG_ENTERED -> {
                    // Applies a green tint to the View.
                    (v as? ImageView)?.setColorFilter(Color.GREEN)

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate()

                    // Returns true; the value is ignored.
                    true
                }

                DragEvent.ACTION_DRAG_LOCATION ->
                    // Ignore the event.
                    true
                DragEvent.ACTION_DRAG_EXITED -> {
                    // Resets the color tint to blue.
                    (v as? ImageView)?.setColorFilter(Color.BLUE)

                    // Invalidates the view to force a redraw in the new tint.
                    v.invalidate()

                    // Returns true; the value is ignored.
                    true
                }
                DragEvent.ACTION_DROP -> {
                    // Gets the item containing the dragged data.
                    val item: ClipData.Item = e.clipData.getItemAt(0)

                    // Gets the text data from the item.
                    val dragData = item.text

                    // Displays a message containing the dragged data.
                    Toast.makeText(context, "Dragged data is $dragData", Toast.LENGTH_LONG).show()

                    // Turns off any color tints.
                    (v as? ImageView)?.clearColorFilter()

                    // Invalidates the view to force a redraw.
                    v.invalidate()

                    // Returns true. DragEvent.getResult() will return true.
                    true
                }

                DragEvent.ACTION_DRAG_ENDED -> {
                    // Turns off any color tinting.
                    (v as? ImageView)?.clearColorFilter()

                    // Invalidates the view to force a redraw.
                    v.invalidate()

                    // Does a getResult(), and displays what happened.
                    when(e.result) {
                        true ->
                            Toast.makeText(context, "The drop was handled.", Toast.LENGTH_LONG)
                        else ->
                            Toast.makeText(context, "The drop didn't work.", Toast.LENGTH_LONG)
                    }.show()

                    // Returns true; the value is ignored.
                    true
                }
                else -> {
                    // An unknown action type was received.
                    Log.e("DragDrop Example", "Unknown action type received by View.OnDragListener.")
                    false
                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}