package com.homairaahmed.bddoctorhub.ui.fragment

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.databinding.FragmentLoginBinding
import com.homairaahmed.bddoctorhub.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val authViewModel : AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickListener()

        binding.btnLogin.setOnClickListener(){
            authViewModel.userName.value = binding.etUserName.text.toString()
            authViewModel.userPass.value = binding.etUserPassword.text.toString()
            if (loginDataValidation()){

            }
        }



    }

    private fun onClickListener() {
        binding.tvDontHaveAccount.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }


        binding.btnLogin.setOnClickListener(){
            authViewModel.userName.value = binding.etUserName.text.toString()
            authViewModel.userPass.value = binding.etUserPassword.text.toString()
            if (loginDataValidation()){

            }
        }
    }

    private fun loginDataValidation() : Boolean {
        when(authViewModel.loginDataValidation()){
            1 -> {
                binding.etUserName.setError("Please enter your username")
                binding.etUserName.requestFocus()
                false
            }
            2 -> {
                binding.etUserPassword.setError("Please enter your password")
                binding.etUserPassword.requestFocus()
                false
            }
        }
        return true
    }


}