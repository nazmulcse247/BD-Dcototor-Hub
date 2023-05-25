package com.homairaahmed.bddoctorhub.ui.fragment

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.adapter.CategoryDoctorAdapter
import com.homairaahmed.bddoctorhub.adapter.SearchDoctorAdapter
import com.homairaahmed.bddoctorhub.data.Doctor
import com.homairaahmed.bddoctorhub.data.Resource
import com.homairaahmed.bddoctorhub.databinding.FragmentDoctorSearchBinding
import com.homairaahmed.bddoctorhub.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [DoctorSearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DoctorSearchFragment : Fragment() {

    private lateinit var binding : FragmentDoctorSearchBinding
    private val categoryViewModel: CategoryViewModel by viewModels()
    private val doctorList = ArrayList<Doctor>()
    private lateinit var searchDoctorAdapter: SearchDoctorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDoctorSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnclickListener()
    }

    private fun setOnclickListener() {

        binding.ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.etDoctorSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                context?.hideKeyboard(binding.etDoctorSearch)
                if (binding.etDoctorSearch.text.toString().trim().isNotBlank())
                    searchDoctor(binding.etDoctorSearch.text.toString().trim())
                else
                    Toast.makeText(context, "Please enter a doctor name", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    private fun searchDoctor(doctorName : String) {

        lifecycleScope.launch {
            categoryViewModel.searchDoctor(doctorName).collect {

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
                        searchDoctorAdapter = SearchDoctorAdapter(requireContext(),doctorList)
                        val layoutManager = LinearLayoutManager(requireContext())
                        binding.rvSearchDoctor.layoutManager = layoutManager
                        binding.rvSearchDoctor.adapter = searchDoctorAdapter
                    }
                }
            }
        }

    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


}