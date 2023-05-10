package com.homairaahmed.bddoctorhub.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.databinding.FragmentIcuBinding
import com.homairaahmed.bddoctorhub.viewmodel.OtherServiceViewModel


class IcuFragment : Fragment() {
    private lateinit var binding : FragmentIcuBinding
    private val otherServiceViewModel : OtherServiceViewModel by viewModels()

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
    }

    private fun setOnCliclListener() {
        TODO("Not yet implemented")
    }

    private fun initView() {
        TODO("Not yet implemented")
    }

}