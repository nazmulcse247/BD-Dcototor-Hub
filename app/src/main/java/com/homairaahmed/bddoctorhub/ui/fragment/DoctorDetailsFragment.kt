package com.homairaahmed.bddoctorhub.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.databinding.FragmentDoctorDetailsBinding


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

        initView()
        setOnclickListener()




    }

    private fun setOnclickListener() {

        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + args.doctor.phone))
            startActivity(intent)
        }

        binding.ivShare.setOnClickListener {
            showSharingDialogAsKotlin("Doctor Name : ${args.doctor.name}\nDoctor Education : ${args.doctor.education}\nDoctor Speciality : ${args.doctor.specility}\nDoctor Professor : ${args.doctor.professor}\nDoctor About : ${args.doctor.about}\nDoctor Chamber : ${args.doctor.chamber}\nDoctor Image : ${args.doctor.image}")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val doctor = args.doctor
        binding.apply {
            tvDoctorName.text = doctor.name
            tvDoctorEducation.text = doctor.education
            tvDoctorSpeciality.text = doctor.specility
            tvDoctorProfessor.text = doctor.professor
            tvAppointmentChamberDetails.text = doctor.about
            tvAppointmentChamber.text = "About ${doctor.name}"
            Glide.with(requireContext()).load(doctor.image).placeholder(R.drawable.male_placeholder).into(ivDoctor)
            doctor.chamber[0].let { tvDoctorAppointmentChamber.text = it }
            doctor.chamber[1].let { tvDoctorAppointmentAddress.text = "Address : $it" }
            doctor.chamber[2].let { tvDoctorAppointmentTime.text = "Visiting Hour : $it" }


        }

    }


    fun showSharingDialogAsKotlin(text: String) {
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, "Share with:"))
    }


}