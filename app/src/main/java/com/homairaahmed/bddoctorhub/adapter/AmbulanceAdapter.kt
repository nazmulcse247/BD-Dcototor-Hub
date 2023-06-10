package com.homairaahmed.bddoctorhub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.homairaahmed.bddoctorhub.data.Ambulance
import com.homairaahmed.bddoctorhub.databinding.AmbulanceLayoutBinding

class AmbulanceAdapter : RecyclerView.Adapter<AmbulanceAdapter.AmbulanceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmbulanceViewHolder {
        val binding = AmbulanceLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AmbulanceViewHolder(binding)
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Ambulance>() {
        override fun areItemsTheSame(oldItem: Ambulance, newItem: Ambulance): Boolean {
            return oldItem.ambulanceId == newItem.ambulanceId
        }

        override fun areContentsTheSame(oldItem: Ambulance, newItem: Ambulance): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallBack)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AmbulanceViewHolder, position: Int) {
        val ambulance = differ.currentList[position]


    }

    inner class AmbulanceViewHolder(private val binding: AmbulanceLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun setData(ambulance: Ambulance) {
            ambulance.apply {
                binding.apply {
                    tvHospitalName.text = ambulanceName
                    tvHospitalAddress.text = ambulanceNumber
                }
            }
        }
    }

}