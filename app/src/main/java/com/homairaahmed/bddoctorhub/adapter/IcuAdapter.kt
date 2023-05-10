package com.homairaahmed.bddoctorhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.homairaahmed.bddoctorhub.data.Icu
import com.homairaahmed.bddoctorhub.databinding.IcuLayoutBinding
import com.homairaahmed.bddoctorhub.databinding.MedicalLayoutBinding

class IcuAdapter(private val context: Context, private val icuList: List<Icu>) : Adapter<IcuAdapter.IcuDetailsViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IcuDetailsViewHolder {
        val binding = IcuLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return IcuDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return icuList.size
    }

    override fun onBindViewHolder(holder: IcuDetailsViewHolder, position: Int) {
        val icu = icuList[position]
        holder.binding.apply {
            tvHospitalName.text = icu.hospitalName
            tvHospitalAddress.text = "Location : ${icu.location}"

        }
    }

    inner class IcuDetailsViewHolder(val binding: IcuLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}