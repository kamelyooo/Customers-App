package com.example.customersapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.customersapp.adapters.UsersAdapter
import com.example.customersapp.databinding.HomeFragmentBinding
import com.example.customersapp.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var userTimeAdded:String
    private lateinit var usersAdapter: UsersAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupUserAdapter()
        viewModel.getAllUsers()
        viewModel.getUsersList().observe(viewLifecycleOwner, Observer {
            usersAdapter.submitList(it)
            hideProgressBar()
        })

        return root
    }
    private fun setupUserAdapter() = binding.usersRecycle.apply {
        usersAdapter = UsersAdapter()
        adapter = usersAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    private fun hideProgressBar(){
        binding.progressBar.visibility=View.GONE
        binding.usersRecycle.visibility=View.VISIBLE
    }
}