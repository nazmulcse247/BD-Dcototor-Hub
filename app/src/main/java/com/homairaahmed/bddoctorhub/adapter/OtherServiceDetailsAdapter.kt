package com.homairaahmed.bddoctorhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.homairaahmed.bddoctorhub.data.Hospital
import com.homairaahmed.bddoctorhub.databinding.MedicalLayoutBinding
import com.homairaahmed.bddoctorhub.ui.fragment.OtherServiceFragmentDirections

class OtherServiceDetailsAdapter(private val context: Context , private val list: List<Hospital>) : Adapter<OtherServiceDetailsAdapter.OtherServiceDetailsViewHolder>(){



    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OtherServiceDetailsViewHolder {
        val binding = MedicalLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return OtherServiceDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OtherServiceDetailsViewHolder, position: Int) {
        val hospital = list[position]
        holder.binding.apply {
            tvHospitalName.text = hospital.hospitalName
        }
        holder.itemView.setOnClickListener {
            val action = OtherServiceFragmentDirections.actionOtherServiceFragmentToCategoryDoctorFragment(hospital.hospitalId)
            it.findNavController().navigate(action)
        }
    }

    inner class OtherServiceDetailsViewHolder(val binding: MedicalLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}