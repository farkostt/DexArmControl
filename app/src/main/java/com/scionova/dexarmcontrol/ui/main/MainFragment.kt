package com.scionova.dexarmcontrol.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.scionova.dexarmcontrol.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        // TODO: Use the ViewModel


        viewModel.setupSquareArrows(binding.btnArrowUp, binding.btnArrowRight, binding.btnArrowDown, binding.btnArrowLeft, binding.btnArrowMiddle)
        viewModel.setupVerticalArrowsRight(binding.btnArrowUpVerticalRight, binding.btnArrowDownVerticalRight, binding.btnArrowMiddleVerticalRight)
        viewModel.setupVerticalArrowsLeft(binding.btnArrowUpVerticalLeft, binding.btnArrowDownVerticalLeft, binding.btnArrowMiddleVerticalLeft)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}