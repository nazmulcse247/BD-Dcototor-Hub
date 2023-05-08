package com.homairaahmed.bddoctorhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.data.Doctor
import com.homairaahmed.bddoctorhub.databinding.MostPopularDoctorLayoutBinding
import com.homairaahmed.bddoctorhub.ui.fragment.DashboardFragmentDirections
import com.homairaahmed.bddoctorhub.utils.ImageUtils

class MostPopularDoctorAdapter(private val context: Context,private val popularDoctorList: List<Doctor>) : RecyclerView.Adapter<MostPopularDoctorAdapter.mostPopularDoctorViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MostPopularDoctorAdapter.mostPopularDoctorViewHolder {
       val binding = MostPopularDoctorLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return mostPopularDoctorViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MostPopularDoctorAdapter.mostPopularDoctorViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            val doctor = popularDoctorList[position]
            tvDoctorName.text = doctor.name
            tvDoctorSpeciality.text = doctor.specility
            tvDoctorEducation.text = doctor.education
            tvDoctorProfessor.text = doctor.professor
            ImageUtils.showNetworkImage(ivDoctor,context,doctor.image)

            holder.itemView.setOnClickListener {
                val action = DashboardFragmentDirections.actionDashboardFragmentToDoctorDetailsFragment(doctor)
                it.findNavController().navigate(action)
            }

        }


    }

    override fun getItemCount(): Int {
        return popularDoctorList.size

    }

    inner class mostPopularDoctorViewHolder(val binding : MostPopularDoctorLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
