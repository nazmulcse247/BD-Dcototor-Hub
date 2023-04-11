package com.homairaahmed.bddoctorhub.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation.findNavController
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.data.Resource
import com.homairaahmed.bddoctorhub.data.User
import com.homairaahmed.bddoctorhub.databinding.FragmentRegistrationBinding
import com.homairaahmed.bddoctorhub.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [RegistrationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class RegistrationFragment : Fragment() {

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

        lifecycle.coroutineScope.launchWhenCreated {
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
                }
            }
        }


        binding.btnSignUp.setOnClickListener{
            val user = User(
                name = "Jahid Hasan",
                image = "",
                email = "example@gmal.com",
                active = true,
                address = "Dhaka, Bangladesh"
            )

            authViewModel.register(binding.etUserEmail.text.toString(), binding.etUserPassword.text.toString(), user)



        }

    }

    private fun onClickListener() {
        TODO("Not yet implemented")
    }


}