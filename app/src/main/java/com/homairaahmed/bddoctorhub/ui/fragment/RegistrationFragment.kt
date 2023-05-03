package com.homairaahmed.bddoctorhub.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.data.User
import com.homairaahmed.bddoctorhub.databinding.FragmentRegistrationBinding
import com.homairaahmed.bddoctorhub.interfaces.ResendRequestCallBack
import com.homairaahmed.bddoctorhub.utils.DialogUtils
import com.homairaahmed.bddoctorhub.utils.NetworkUtils
import com.homairaahmed.bddoctorhub.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [RegistrationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class RegistrationFragment : Fragment(),ResendRequestCallBack {

    private lateinit var binding: FragmentRegistrationBinding
    private val authViewModel: AuthViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationBinding.inflate(inflater,container,false)
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
                    Toast.makeText(context, "Registration Done , Please Sign in", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
                }
            }
        }




    }

    private fun onClickListener() {
        binding.tvAlreadyHaveAccountSingIn.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }

        binding.btnSignUp.setOnClickListener{

            val user = User(
                name = binding.etUserName.text.toString(),
                image = "",
                email = binding.etUserEmail.text.toString(),
                active = true,
                address = "Dhaka, Bangladesh"
            )
            authViewModel.users = user
            authViewModel.userName.value = binding.etUserName.text.toString()
            authViewModel.userEmail.value = binding.etUserEmail.text.toString()
            authViewModel.userPass.value = binding.etUserPassword.text.toString()

            if (registerDataValidation()) {
                registrattionUiObserver()

            }
        }

    }

    fun registrattionUiObserver () {

        if (NetworkUtils.isInternetAvailable(requireContext())){
            authViewModel.register()
        }
        else {
            DialogUtils.customDialog(requireContext(),this)
        }

    }

    fun registerDataValidation(): Boolean {
        when(authViewModel.registerDataValidation()){
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
            3 -> {
                binding.etUserEmail.setError("Please enter your email")
                binding.etUserEmail.requestFocus()
                false
            }
        }
        return true
    }

    override fun resendRequest() {
        if (registerDataValidation()) {
            registrattionUiObserver()
        }
    }


}