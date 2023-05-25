package com.homairaahmed.bddoctorhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.data.Doctor
import com.homairaahmed.bddoctorhub.databinding.CategoryDoctorLayoutBinding
import com.homairaahmed.bddoctorhub.ui.fragment.CategoryDoctorFragmentDirections
import com.homairaahmed.bddoctorhub.ui.fragment.DoctorSearchFragmentDirections


class SearchDoctorAdapter(private val context: Context,private val list: List<Doctor>) : Adapter<SearchDoctorAdapter.SearchDoctorViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchDoctorViewHolder {
        val binding = CategoryDoctorLayoutBinding.inflate(LayoutInflater.from(context),parent,false)
        return SearchDoctorViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: SearchDoctorViewHolder, position: Int) {
        val doctor = list[position]
        holder.binding.apply {
            Glide.with(context).load(doctor.image).placeholder(R.drawable.male_placeholder).into(this.ivDoctor)
            tvDoctorName.text = doctor.name
            tvDoctorEducation.text = doctor.education
            tvDoctorSpeciality.text = doctor.specility
            tvDoctorProfessor.text = doctor.professor
            holder.itemView.setOnClickListener {
                val action = DoctorSearchFragmentDirections.actionDoctorSearchFragmentToDoctorDetailsFragment(doctor)
                it.findNavController().navigate(action)
            }

        }

    }

    inner class SearchDoctorViewHolder(val binding: CategoryDoctorLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}