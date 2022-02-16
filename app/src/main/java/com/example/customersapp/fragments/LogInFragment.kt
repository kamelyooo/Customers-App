package com.example.customersapp.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.customersapp.R
import com.example.customersapp.databinding.LogInFragmentBinding
import com.example.customersapp.databinding.SignUpFragmentBinding
import com.example.customersapp.viewModels.LogInViewModel
import com.example.customersapp.viewModels.SignUpViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class LogInFragment : Fragment() {

    private val viewModel: LogInViewModel by viewModels()
    private var _binding: LogInFragmentBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var mAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LogInFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.LogInBtn.setOnClickListener {
            if (checkFields()) {
                showProgressBar()
                viewModel. loginUser(binding.EmailLogInET.text.toString(),binding.passwordLogInET.text.toString())
            }
        }

        viewModel.getIsLoginSuccess().observe(viewLifecycleOwner, Observer { success->
            if (success){

                navigateToHomeFrg()
                hideProgressBar()
            }else {
                Toast.makeText(requireContext(), viewModel.getErrorMsg(), Toast.LENGTH_SHORT).show()
                hideProgressBar()
            }
        })

        binding.SignUpTV.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_signUpFragment)
        }
        return root
    }

    private fun showProgressBar(){
        binding.PBLogInLoading.visibility=View.VISIBLE
        binding.LogInBtn.isEnabled=false
    }
    private fun hideProgressBar(){
        binding.PBLogInLoading.visibility=View.GONE
        binding.LogInBtn.isEnabled=true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }



    private fun checkFields(): Boolean {
        if (binding.EmailLogInET.text.toString() == "" || binding.EmailLogInET.text.toString()
                .isEmpty()
        )
            binding.textInputEmailLogIn.error = "Enter Email"
        if (binding.passwordLogInET.text.toString() == "" || binding.passwordLogInET.text.toString()
                .isEmpty()
        )
            binding.textInputpasswordLogIn.error = "Enter Password"
        val email = binding.EmailLogInET.text.toString()
        val pass = binding.passwordLogInET.text.toString()
        return !(email.isEmpty() || pass.isEmpty())
    }

    override fun onStart() {
        super.onStart()
        mAuth.currentUser?.let {
            navigateToHomeFrg()
        }

    }

    private fun navigateToHomeFrg() {
        findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
    }
}