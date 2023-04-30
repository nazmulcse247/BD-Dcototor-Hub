package com.homairaahmed.bddoctorhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.homairaahmed.bddoctorhub.data.OtherService
import com.homairaahmed.bddoctorhub.databinding.OtherServiceLayoutBinding
import com.homairaahmed.bddoctorhub.utils.ImageUtils

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
            //ImageUtils.showNetworkImage(ivOtherService,context,otherService.serviceImage)
            Glide.with(context).load(otherService.serviceImage).transition(DrawableTransitionOptions.withCrossFade(500)).into(ivOtherService)
            tvOtherServiceName.text = otherService.serviceName
        }
    }

    inner class OtherServiceViewHolder(val binding: OtherServiceLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}