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
import androidx.navigation.fragment.findNavController
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



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SignUpFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.SingUpBtn.setOnClickListener {
            if (checkFields()){
                showProgressBar()
                viewModel.  sighUpUser(binding.EmailET.text.toString(),binding.PassWordET.text.toString(),
                    getCurrentTime(),binding.NameET.text.toString())
            }

        }

        viewModel.isSignUpSuccess().observe(viewLifecycleOwner, Observer { success->
            if (success){
                navigateToHomeFrg()
                hideProgressBar()
            }else {
                hideProgressBar()
                Toast.makeText(requireContext(), viewModel.getErrorMsg(), Toast.LENGTH_SHORT).show()
            }

        })
        binding.logInTv.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_logInFragment)
        }
        return root

    }
    private fun checkFields(): Boolean {
        val email = binding.EmailET.text.toString()
        val pass = binding.PassWordET.text.toString()
        val name = binding.NameET.text.toString()
        if (binding.EmailET.text.toString() == "" || binding.EmailET.text.toString()
                .isEmpty()
        )
            binding.textInputEmail.error = "Enter Email"
        if (binding.PassWordET.text.toString() == "" || binding.PassWordET.text.toString()
                .isEmpty()
        )
            binding.textInputPass.error = "Enter Password"

        if (binding.NameET.text.toString() == "" || binding.NameET.text.toString()
                .isEmpty()
        )
            binding.textInputName.error = "Enter Name"

        return !(email.isEmpty() || pass.isEmpty()||name.isEmpty())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
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
    private fun navigateToHomeFrg() {
        findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
    }
}