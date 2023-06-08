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
import androidx.recyclerview.widget.LinearLayoutManager
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.adapter.CategoryDoctorAdapter
import com.homairaahmed.bddoctorhub.adapter.HealthTipsAdapter
import com.homairaahmed.bddoctorhub.data.Resource
import com.homairaahmed.bddoctorhub.databinding.FragmentHealthTipsBinding
import com.homairaahmed.bddoctorhub.viewmodel.OtherServiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HealthTipsFragment : Fragment() {
    private lateinit var binding: FragmentHealthTipsBinding
    private val otherServiceViewModel : OtherServiceViewModel by viewModels()
    private val healthTipsAdapter by lazy { HealthTipsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHealthTipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
        getHealthTipsUIObserver()

    }

    private fun getHealthTipsUIObserver() {

        lifecycleScope.launch {

            otherServiceViewModel.getHealthTips().collect {
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
                            healthTipsAdapter.differ.submitList(it.data)
                            binding.rvHealthTips.apply {
                                adapter = healthTipsAdapter
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

    private fun setOnClickListener() {
        binding.apply {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

}