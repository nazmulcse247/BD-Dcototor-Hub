package com.homairaahmed.bddoctorhub.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.adapter.AmbulanceAdapter
import com.homairaahmed.bddoctorhub.databinding.FragmentAmbulanceBinding


/**
 * A simple [Fragment] subclass.
 * Use the [AmbulanceFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AmbulanceFragment : Fragment() {
    private lateinit var binding : FragmentAmbulanceBinding
    private val ambulanceAdapter by lazy { AmbulanceAdapter() }

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
    }

}