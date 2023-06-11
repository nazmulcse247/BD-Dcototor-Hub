package com.homairaahmed.bddoctorhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.homairaahmed.bddoctorhub.data.Ambulance
import com.homairaahmed.bddoctorhub.databinding.AmbulanceLayoutBinding
import com.homairaahmed.bddoctorhub.utils.DialerUtils
import com.homairaahmed.bddoctorhub.utils.DialerUtils.Companion.dailNumber

class AmbulanceAdapter : RecyclerView.Adapter<AmbulanceAdapter.AmbulanceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AmbulanceViewHolder {
        val binding = AmbulanceLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AmbulanceViewHolder(binding)
    }

    private val diffCallBack = object : DiffUtil.ItemCallback<Ambulance>() {
        override fun areItemsTheSame(oldItem: Ambulance, newItem: Ambulance): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Ambulance, newItem: Ambulance): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffCallBack)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: AmbulanceViewHolder, position: Int) {
        val ambulance = differ.currentList[position]
        holder.setData(ambulance,holder.itemView.context)

    }

    inner class AmbulanceViewHolder(private val binding: AmbulanceLayoutBinding) : RecyclerView.ViewHolder(binding.root){

        fun setData(ambulance: Ambulance,context : Context) {
            ambulance.apply {
                binding.apply {
                    tvHospitalName.text = name
                    tvHospitalAddress.text = location
                    btnCallToBooking.setOnClickListener {
                        dailNumber(context, ambulance.number)
                    }
                }
            }
        }
    }

}