package com.example.customersapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.customersapp.R
import com.example.customersapp.databinding.SignUpFragmentBinding
import com.example.customersapp.viewModels.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: SignUpFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels()

    @Inject
    lateinit var userTimeAdded:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignUpFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.SingUpBtn.setOnClickListener {
            showProgressBar()
            viewModel.  sighUpUser(binding.EmailET.text.toString(),binding.PassWordET.text.toString(),
                getCurrentTime(),binding.NameET.text.toString())
        }

        viewModel.isSignUpSuccess().observe(viewLifecycleOwner, Observer { success->
            if (success){
//                navigateToHomeFrg()
                hideProgressBar()
            }else {
                hideProgressBar()
                Toast.makeText(requireContext(), viewModel.getErrorMsg(), Toast.LENGTH_SHORT).show()
            }

        })
        return root

    }

    private fun getCurrentTime():String{
        val timeInMillis = Calendar.getInstance().timeInMillis
        val dateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault())
       return dateFormat.format(timeInMillis)
    }

    private fun showProgressBar(){
        binding.progressBarLoading.visibility=View.VISIBLE
        binding.SingUpBtn.isEnabled=false
    }
    private fun hideProgressBar(){
        binding.progressBarLoading.visibility=View.GONE
        binding.SingUpBtn.isEnabled=true
    }

}