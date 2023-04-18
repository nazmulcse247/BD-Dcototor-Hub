package com.homairaahmed.bddoctorhub.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.adapter.SliderAdapter
import com.homairaahmed.bddoctorhub.databinding.FragmentDashboardBinding
import com.homairaahmed.bddoctorhub.utils.networkstate.displayToast
import com.homairaahmed.bddoctorhub.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
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

        /*val imageSlider = view.findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = ArrayList<SlideModel>()
        val imageList1 = ArrayList<Int>()
        imageList1.add(R.drawable.doc_1)
        imageList1.add(R.drawable.doc_2)
        imageList1.add(R.drawable.doc_3)

        for (i in 0 until imageList1.size) {
            imageList.add(SlideModel(imageList1[i]))
        }

        imageSlider.setImageList(imageList, ScaleTypes.FIT)*/

        val imageList = ArrayList<Int>()
        imageList.add(R.drawable.slider_1)
        imageList.add(R.drawable.slider_2)

        binding.vpSlider.adapter = SliderAdapter(imageList,requireContext())
        binding.vpSlider.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.wormDotsIndicator.attachTo(binding.vpSlider)



        userDataUIObserver()

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


}
