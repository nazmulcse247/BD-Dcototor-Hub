package com.homairaahmed.bddoctorhub.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.databinding.FragmentLoginBinding
import com.homairaahmed.bddoctorhub.interfaces.ResendRequestCallBack
import com.homairaahmed.bddoctorhub.utils.DialogUtils
import com.homairaahmed.bddoctorhub.utils.NetworkUtils
import com.homairaahmed.bddoctorhub.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : Fragment(),ResendRequestCallBack {

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

        lifecycleScope.launch {
            authViewModel.user.collect {
                if (it.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                    //this@MainActivity.displayToast(it.error)
                    Toast.makeText(context, it.error, Toast.LENGTH_SHORT).show()
                }
                it.data?.let {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
                }
            }
        }





    }

    private fun onClickListener() {
        binding.tvDontHaveAccountSingUp.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }


        binding.btnLogin.setOnClickListener {
            authViewModel.userEmail.value = binding.etUserName.text.toString()
            authViewModel.userPass.value = binding.etUserPassword.text.toString()
            if (loginDataValidation()){
                loginUIObserver()

            }
        }
    }

    fun loginUIObserver() {

        if (NetworkUtils.isInternetAvailable(requireContext())){
            authViewModel.login()
        }

        else {
            DialogUtils.customDialog(requireContext(),this)
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



    override fun resendRequest() {
        if (loginDataValidation()){
            loginUIObserver()
        }
    }


}