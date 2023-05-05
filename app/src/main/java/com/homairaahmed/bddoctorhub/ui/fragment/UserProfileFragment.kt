package com.homairaahmed.bddoctorhub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.databinding.FragmentUserProfileBinding
import com.homairaahmed.bddoctorhub.utils.preference.UserAuthPref
import com.homairaahmed.bddoctorhub.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [UserProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class UserProfileFragment : Fragment() {
    private lateinit var binding: FragmentUserProfileBinding
    private val authViewModel : AuthViewModel by viewModels()

    @Inject
    lateinit var authPref : UserAuthPref


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        userDataUIObserver()
        setOnclickListener()

    }

    private fun userDataUIObserver() {
        authViewModel.getUserData()

        lifecycleScope.launch {
            authViewModel.userData.collect {

                if (it.isLoading) {
                    binding.tvUserName.visibility = View.INVISIBLE
                    binding.progressBar.visibility = View.VISIBLE
                }
                if (it.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                }

                it.data?.let {
                    binding.progressBar.visibility = View.GONE
                    binding.tvUserName.visibility = View.VISIBLE
                    binding.tvUserName.text = it.name
                    binding.tvUserGmail.text = it.email


                }
            }
        }


    }

    private fun setOnclickListener() {
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnLogOut.setOnClickListener {
            authPref.saveUserAuthData(false)
            findNavController().navigate(R.id.action_userProfileFragment_to_loginFragment)
        }


    }


}