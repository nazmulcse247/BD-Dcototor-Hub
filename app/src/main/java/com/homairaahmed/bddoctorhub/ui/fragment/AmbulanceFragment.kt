package com.homairaahmed.bddoctorhub.ui.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.adapter.AmbulanceAdapter
import com.homairaahmed.bddoctorhub.data.Resource
import com.homairaahmed.bddoctorhub.databinding.FragmentAmbulanceBinding
import com.homairaahmed.bddoctorhub.viewmodel.OtherServiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [AmbulanceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AmbulanceFragment : Fragment() {
    private lateinit var binding : FragmentAmbulanceBinding
    private val ambulanceAdapter by lazy { AmbulanceAdapter() }
    private val otherServiceViewModel : OtherServiceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAmbulanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
        ambulanceServiceUIObserver()
    }

    private fun setOnClickListener() {
        binding.apply {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun ambulanceServiceUIObserver() {
        lifecycleScope.launch {
            otherServiceViewModel.getAmbulance().collect {
                when(it){
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Log.e(ContentValues.TAG, "mostPopularUIObserver: "+it.message)
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        if (it.data!!.isNotEmpty()) {
                            ambulanceAdapter.differ.submitList(it.data)
                            binding.rvAmulance.apply {
                                adapter = ambulanceAdapter
                            }
                        }
                        else {
                            binding.tvNoDoctorFound.visibility = View.VISIBLE
                        }

                    }
                }
            }
        }
    }


}