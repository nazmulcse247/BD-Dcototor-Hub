package com.homairaahmed.bddoctorhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.homairaahmed.bddoctorhub.databinding.SliderLayoutBinding

class SliderAdapter(private val imageList: List<Int>,private val context : Context) : Adapter<SliderAdapter.SliderViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding = SliderLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SliderViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val image = imageList[position]
        Glide.with(context).load(image).into(holder.binding.ivSlider)
    }

    inner class SliderViewHolder(val binding: SliderLayoutBinding) : ViewHolder(binding.root)

}