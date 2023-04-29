package com.homairaahmed.bddoctorhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.homairaahmed.bddoctorhub.data.Doctor
import com.homairaahmed.bddoctorhub.databinding.MostPopularDoctorLayoutBinding

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
            ivDoctor.setImageDrawable(context.getDrawable(popularDoctorList[position].doctorImage))
            tvDoctorName.text = doctor.doctorName
            tvDoctorSpeciality.text = doctor.doctorSpeciality
            tvDoctorEducation.text = doctor.doctorDegree
            tvDoctorProfessor.text = doctor.doctorProfessor

        }
    }

    override fun getItemCount(): Int {
        return popularDoctorList.size

    }

    inner class mostPopularDoctorViewHolder(val binding : MostPopularDoctorLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}
