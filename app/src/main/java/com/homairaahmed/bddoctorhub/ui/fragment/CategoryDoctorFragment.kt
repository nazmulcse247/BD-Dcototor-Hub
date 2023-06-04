package com.homairaahmed.bddoctorhub.ui.fragment

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.adapter.CategoryDoctorAdapter
import com.homairaahmed.bddoctorhub.adapter.MostPopularDoctorAdapter
import com.homairaahmed.bddoctorhub.data.Doctor
import com.homairaahmed.bddoctorhub.data.Resource
import com.homairaahmed.bddoctorhub.databinding.FragmentCategoryDoctorBinding
import com.homairaahmed.bddoctorhub.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [CategoryDoctorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CategoryDoctorFragment : Fragment() {

    private lateinit var binding : FragmentCategoryDoctorBinding
    private val categoryViewModel : CategoryViewModel by viewModels()
    private val args by navArgs<CategoryDoctorFragmentArgs>()
    private val doctorList = ArrayList<Doctor>()
    private lateinit var categoryDoctorAdapter: CategoryDoctorAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCategoryDoctorBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        categoryDoctorUIObserver()
    }

    private fun initView() {
        categoryViewModel.categoryName.value = args.category
        binding.tvDoctorCategory.text = args.category
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun categoryDoctorUIObserver() {

        lifecycleScope.launch {

            categoryViewModel.getCategoryDoctor().collect {
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
                        doctorList.clear()
                        if (it.data!!.isNotEmpty()) {
                            categoryDoctorAdapter = CategoryDoctorAdapter(requireContext(),it.data)
                            val layoutManager = LinearLayoutManager(requireContext())
                            binding.rvCategoryDoctor.layoutManager = layoutManager
                            binding.rvCategoryDoctor.adapter = categoryDoctorAdapter
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