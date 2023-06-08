package com.homairaahmed.bddoctorhub.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.databinding.FragmentHealthTipsDetailsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HealthTipsDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HealthTipsDetailsFragment : Fragment() {

    private val args by navArgs<HealthTipsDetailsFragmentArgs>()
    private lateinit var binding : FragmentHealthTipsDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHealthTipsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        val doctorTips = args.healthTips
        binding.apply {
            Glide.with(requireContext()).load(doctorTips.image).into(this.ivHealthTipsImage)
            tvHealthTipsContent.text = doctorTips.content
        }
    }


}