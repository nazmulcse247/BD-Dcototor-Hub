package com.homairaahmed.bddoctorhub.ui.fragment

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.adapter.CategoryAdapter
import com.homairaahmed.bddoctorhub.adapter.MostPopularDoctorAdapter
import com.homairaahmed.bddoctorhub.adapter.OtherServiceAdapter
import com.homairaahmed.bddoctorhub.adapter.SliderAdapter
import com.homairaahmed.bddoctorhub.data.Category
import com.homairaahmed.bddoctorhub.data.Doctor
import com.homairaahmed.bddoctorhub.data.OtherService
import com.homairaahmed.bddoctorhub.data.Resource
import com.homairaahmed.bddoctorhub.databinding.FragmentDashboardBinding
import com.homairaahmed.bddoctorhub.viewmodel.AuthViewModel
import com.homairaahmed.bddoctorhub.viewmodel.DashboardViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private val authViewModel: AuthViewModel by viewModels()
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var mostPopularAdapter: MostPopularDoctorAdapter
    private lateinit var otherServiceAdapter: OtherServiceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val imageList = ArrayList<Int>()
        imageList.add(R.drawable.slider_1)
        imageList.add(R.drawable.slider_2)
        imageList.add(R.drawable.slider_4)

        binding.vpSlider.adapter = SliderAdapter(imageList,requireContext())
        binding.vpSlider.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.wormDotsIndicator.attachTo(binding.vpSlider)



        otherServiceUIObserver()
        userDataUIObserver()
        categoryUIObserver()
        mostPopularUIObserver()


    }



    private fun mostPopularUIObserver() {
        val popularList = ArrayList<Doctor>()

        popularList.add(Doctor("Dr. Homaira Ahmed","Gynecology, Obstetrics, Gynecological Cancer Specialist & Surgeon","MBBS, DGO, MCPS, MS (OBGYN)","Bangabandhu Sheikh Mujib Medical University Hospital","5",R.drawable.male_placeholder))
        popularList.add(Doctor("Dr. Homaira Ahmed","Gynecology, Obstetrics, Gynecological Cancer Specialist & Surgeon","MBBS, DGO, MCPS, MS (OBGYN)","Bangabandhu Sheikh Mujib Medical University Hospital","5",R.drawable.male_placeholder))
        popularList.add(Doctor("Dr. Homaira Ahmed","Gynecology, Obstetrics, Gynecological Cancer Specialist & Surgeon","MBBS, DGO, MCPS, MS (OBGYN)","Bangabandhu Sheikh Mujib Medical University Hospital","5",R.drawable.male_placeholder))
        popularList.add(Doctor("Dr. Homaira Ahmed","Gynecology, Obstetrics, Gynecological Cancer Specialist & Surgeon","MBBS, DGO, MCPS, MS (OBGYN)","Bangabandhu Sheikh Mujib Medical University Hospital","5",R.drawable.male_placeholder))
        popularList.add(Doctor("Dr. Homaira Ahmed","Gynecology, Obstetrics, Gynecological Cancer Specialist & Surgeon","MBBS, DGO, MCPS, MS (OBGYN)","Bangabandhu Sheikh Mujib Medical University Hospital","5",R.drawable.male_placeholder))

        mostPopularAdapter = MostPopularDoctorAdapter(requireContext(),popularList)
        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rvPopularDoctor.layoutManager = layoutManager
        binding.rvPopularDoctor.adapter = mostPopularAdapter
    }

    private fun categoryUIObserver() {
        val categoryList = ArrayList<Category>()

        lifecycleScope.launch {
            dashboardViewModel.getAllCategory.collect {
                when(it){
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Log.e(TAG, "categoryUIObserver: "+it.message)

                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        categoryList.clear()
                        it.data?.let { it1 -> categoryList.addAll(it1) }
                        categoryAdapter = CategoryAdapter(categoryList,requireContext())
                        val layoutManager = GridLayoutManager(requireContext(),4)
                        binding.rvCategory.layoutManager = layoutManager
                        binding.rvCategory.adapter = categoryAdapter
                        categoryAdapter.differ.submitList(categoryList)
                    }
                }

            }
        }


    }

    @SuppressLint("SetTextI18n")
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
                    binding.tvUserName.text = "Hi,${it.name}"
                }
            }
        }


    }

    private fun otherServiceUIObserver() {

        val otherServiceList = ArrayList<OtherService>()

        lifecycleScope.launch {
            dashboardViewModel.getOtherService.collect {
                when(it){
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Log.e(TAG, "categoryUIObserver: "+it.message)
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        //otherServiceList.clear()
                        it.data?.let { it2 -> otherServiceList.addAll(it2) }
                        otherServiceAdapter = OtherServiceAdapter(requireContext(),otherServiceList)
                        binding.rvOtherService.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,false)
                        binding.rvOtherService.adapter = otherServiceAdapter
                        otherServiceAdapter.differ.submitList(otherServiceList)
                    }
                }

            }
        }


    }


}
