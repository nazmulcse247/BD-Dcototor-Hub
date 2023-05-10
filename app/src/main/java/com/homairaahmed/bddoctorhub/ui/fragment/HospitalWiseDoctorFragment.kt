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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.homairaahmed.bddoctorhub.adapter.CategoryDoctorAdapter
import com.homairaahmed.bddoctorhub.adapter.HospitalDoctorAdapter
import com.homairaahmed.bddoctorhub.data.Doctor
import com.homairaahmed.bddoctorhub.data.Resource
import com.homairaahmed.bddoctorhub.databinding.FragmentHospitalWiseDoctorBinding
import com.homairaahmed.bddoctorhub.viewmodel.OtherServiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HospitalWiseDoctorFragment : Fragment() {

    private lateinit var binding: FragmentHospitalWiseDoctorBinding
    private val args by navArgs<HospitalWiseDoctorFragmentArgs>()
    private val otherServiceViewModel: OtherServiceViewModel by viewModels()
    private val doctorList = ArrayList<Doctor>()
    private lateinit var hospitalDoctorAdapter: HospitalDoctorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentHospitalWiseDoctorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        medicalWiseDoctorUIObserver()
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.apply {
            ivBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initViews() {

        otherServiceViewModel.hospitalName.value = args.hospitalName
        binding.tvOtherServiceName.text = args.hospital

    }

    private fun medicalWiseDoctorUIObserver() {

        lifecycleScope.launch {
            otherServiceViewModel.getCategoryDoctor().collect {
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
                        it.data?.let { it1 -> doctorList.addAll(it1) }
                        hospitalDoctorAdapter = HospitalDoctorAdapter(requireContext(),doctorList)
                        val layoutManager = LinearLayoutManager(requireContext())
                        binding.rvOtherService.layoutManager = layoutManager
                        binding.rvOtherService.adapter = hospitalDoctorAdapter
                    }
                }

            }
        }


    }

}