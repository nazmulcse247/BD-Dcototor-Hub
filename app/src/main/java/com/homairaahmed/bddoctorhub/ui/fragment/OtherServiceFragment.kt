package com.homairaahmed.bddoctorhub.ui.fragment

import android.content.ContentValues
import android.content.Context
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
import com.homairaahmed.bddoctorhub.adapter.OtherServiceDetailsAdapter
import com.homairaahmed.bddoctorhub.data.Hospital
import com.homairaahmed.bddoctorhub.data.Resource
import com.homairaahmed.bddoctorhub.databinding.FragmentOtherServiceBinding
import com.homairaahmed.bddoctorhub.viewmodel.CategoryViewModel
import com.homairaahmed.bddoctorhub.viewmodel.OtherServiceViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [OtherServiceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class OtherServiceFragment : Fragment() {

    private lateinit var binding: FragmentOtherServiceBinding
    private var context : Context? = null
    private val args by navArgs<OtherServiceFragmentArgs>()
    private val otherServiceViewModel : OtherServiceViewModel by viewModels()
    private lateinit var otherServiceDetailsAdapter: OtherServiceDetailsAdapter
    private val hospitalList = ArrayList<Hospital>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOtherServiceBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setOnClickListeners()
        medicalWiseDoctorUIObserver()
    }

    private fun medicalWiseDoctorUIObserver() {

        lifecycleScope.launch {
            otherServiceViewModel.getHospital().collect {
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
                        hospitalList.clear()
                        it.data?.let { it1 -> hospitalList.addAll(it1) }
                        otherServiceDetailsAdapter = OtherServiceDetailsAdapter(requireContext(),hospitalList)
                        val layoutManager = LinearLayoutManager(requireContext())
                        binding.rvOtherService.layoutManager = layoutManager
                        binding.rvOtherService.adapter = otherServiceDetailsAdapter
                    }
                }

            }
        }


    }

    private fun initView() {
        val serviceName = args.serviceName
        binding.tvOtherServiceName.text = serviceName.serviceName.toString()
    }

    private fun setOnClickListeners() {

        binding.apply {
            ivBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }


}