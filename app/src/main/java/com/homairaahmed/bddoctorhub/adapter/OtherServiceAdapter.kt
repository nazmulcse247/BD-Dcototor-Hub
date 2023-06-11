package com.homairaahmed.bddoctorhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.data.OtherService
import com.homairaahmed.bddoctorhub.databinding.OtherServiceLayoutBinding
import com.homairaahmed.bddoctorhub.ui.fragment.DashboardFragmentDirections

class OtherServiceAdapter(
    private val context: Context,
    private val otherServiceList: List<OtherService>
) : RecyclerView.Adapter<OtherServiceAdapter.OtherServiceViewHolder>() {


    private val diffUtil = object : DiffUtil.ItemCallback<OtherService>() {
        override fun areItemsTheSame(oldItem: OtherService, newItem: OtherService): Boolean {
            return oldItem.serviceId == newItem.serviceId
        }

        override fun areContentsTheSame(oldItem: OtherService, newItem: OtherService): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherServiceViewHolder {
        val binding = OtherServiceLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return OtherServiceViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return otherServiceList.size
    }

    override fun onBindViewHolder(holder: OtherServiceViewHolder, position: Int) {
        val otherService = otherServiceList[position]
        holder.binding.apply {
            Glide.with(context).load(otherService.serviceImage).into(this.ivOtherService)
            tvOtherServiceName.text = otherService.serviceName
            holder.itemView.setOnClickListener {
                if (otherService.serviceId == "hospital104") {
                    val action = DashboardFragmentDirections.actionDashboardFragmentToOtherServiceFragment(otherService)
                    it.findNavController().navigate(action)
                    return@setOnClickListener
                }
                else if (otherService.serviceId == "emergency105"){
                    it.findNavController().navigate(R.id.action_dashboardFragment_to_icuFragment)
                    return@setOnClickListener
                }

                else if (otherService.serviceId.equals("healthcare102")){
                    it.findNavController().navigate(R.id.action_dashboardFragment_to_healthTipsFragment)
                    return@setOnClickListener
                }

                else if (otherService.serviceId.equals("ambulanceservice101")){
                    it.findNavController().navigate(R.id.action_dashboardFragment_to_ambulanceFragment)
                    return@setOnClickListener
                }


            }
        }
    }

    inner class OtherServiceViewHolder(val binding: OtherServiceLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}