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
import androidx.recyclerview.widget.LinearLayoutManager
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.adapter.HospitalDoctorAdapter
import com.homairaahmed.bddoctorhub.adapter.IcuAdapter
import com.homairaahmed.bddoctorhub.data.Icu
import com.homairaahmed.bddoctorhub.data.Resource
import com.homairaahmed.bddoctorhub.databinding.FragmentIcuBinding
import com.homairaahmed.bddoctorhub.viewmodel.OtherServiceViewModel
import kotlinx.coroutines.launch


class IcuFragment : Fragment() {
    private lateinit var binding : FragmentIcuBinding
    private val otherServiceViewModel : OtherServiceViewModel by viewModels()
    private val icuList = ArrayList<Icu>()
    private lateinit var icuAdapter: IcuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIcuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setOnCliclListener()
        icuUIObserver()
    }

    private fun icuUIObserver() {

        lifecycleScope.launch {
            otherServiceViewModel.getIcu().collect {
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
                        icuList.clear()
                        it.data?.let { it1 -> icuList.addAll(it1) }
                        icuAdapter = IcuAdapter(requireContext(),icuList)
                        val layoutManager = LinearLayoutManager(requireContext())
                        binding.rvOtherService.layoutManager = layoutManager
                        binding.rvOtherService.adapter = icuAdapter
                    }
                }

            }
        }

    }

    private fun setOnCliclListener() {
        TODO("Not yet implemented")
    }

    private fun initView() {
        TODO("Not yet implemented")
    }

}