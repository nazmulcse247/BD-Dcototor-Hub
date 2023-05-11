package com.homairaahmed.bddoctorhub.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.homairaahmed.bddoctorhub.data.Icu
import com.homairaahmed.bddoctorhub.databinding.IcuLayoutBinding
import com.homairaahmed.bddoctorhub.databinding.MedicalLayoutBinding
import com.homairaahmed.bddoctorhub.utils.DialerUtils.Companion.dailNumber
import com.homairaahmed.bddoctorhub.utils.MapUtils
import com.homairaahmed.bddoctorhub.utils.MapUtils.Companion.openMap

class IcuAdapter(private val context: Context, private val icuList: List<Icu>) : Adapter<IcuAdapter.IcuDetailsViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IcuDetailsViewHolder {
        val binding = IcuLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return IcuDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return icuList.size
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun onBindViewHolder(holder: IcuDetailsViewHolder, position: Int) {
        val icu = icuList[position]
        holder.binding.apply {
            tvHospitalName.text = icu.hospitalName
            tvHospitalAddress.text = "Location : ${icu.location}"
            ivLocation.setOnClickListener {

                openMap(icu.latitude,icu.longitude,context)
            }
            btnCallToBooking.setOnClickListener {
                dailNumber(context,icu.number)
            }

        }
    }

    inner class IcuDetailsViewHolder(val binding: IcuLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}