package com.homairaahmed.bddoctorhub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupActionBarWithNavController
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.databinding.FragmentDoctorDetailsBinding
import com.homairaahmed.bddoctorhub.ui.activity.MainActivity

/**
 * A simple [Fragment] subclass.
 * Use the [DoctorDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DoctorDetailsFragment : Fragment() {

    private lateinit var binding : FragmentDoctorDetailsBinding
    private val args by navArgs<DoctorDetailsFragmentArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDoctorDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val doctor = args.doctor
        doctor.chamber[0].let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }



}