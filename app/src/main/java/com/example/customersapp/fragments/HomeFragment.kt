package com.example.customersapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.customersapp.R
import com.example.customersapp.databinding.HomeFragmentBinding
import com.example.customersapp.databinding.LogInFragmentBinding
import com.example.customersapp.viewModels.HomeViewModel
import com.example.customersapp.viewModels.LogInViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var userTimeAdded:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.tv.text=userTimeAdded
        return root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}