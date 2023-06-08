package com.homairaahmed.bddoctorhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.homairaahmed.bddoctorhub.R
import com.homairaahmed.bddoctorhub.data.HealthTips
import com.homairaahmed.bddoctorhub.databinding.HealthTipsLayoutBinding

class HealthTipsAdapter()  : RecyclerView.Adapter<HealthTipsAdapter.HealthTipsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HealthTipsViewHolder {
        val binding = HealthTipsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HealthTipsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HealthTipsViewHolder, position: Int) {
        val currentItem = differ.currentList[position]
        holder.setData(currentItem,holder.itemView.context)
    }

    private val diffUtilCallBack = object : DiffUtil.ItemCallback<HealthTips>() {
        override fun areItemsTheSame(oldItem: HealthTips, newItem: HealthTips): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: HealthTips, newItem: HealthTips): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffUtilCallBack)

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class HealthTipsViewHolder(val binding: HealthTipsLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun setData(healthTips: HealthTips,context : Context) {
            healthTips.apply {
                binding.apply {
                    Glide.with(itemView.context).load(image).placeholder(ContextCompat.getDrawable(context,
                        R.drawable.placeholder)).into(ivHealthTips)
                    tvHealthTipsTitle.text = title
                    tvHealthTipsPublishDate.text = publised
                    tvHealthTipsContent.text = content
                }
            }
        }
    }


}