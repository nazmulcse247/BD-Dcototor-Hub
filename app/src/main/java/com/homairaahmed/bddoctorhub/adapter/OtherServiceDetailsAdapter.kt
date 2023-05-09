package com.homairaahmed.bddoctorhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.homairaahmed.bddoctorhub.data.Medical
import com.homairaahmed.bddoctorhub.databinding.MedicalLayoutBinding

class OtherServiceDetailsAdapter(private val context: Context , private val list: List<Medical>) : Adapter<OtherServiceDetailsAdapter.OtherServiceDetailsViewHolder>(){

    inner class OtherServiceDetailsViewHolder(val binding: MedicalLayoutBinding) : RecyclerView.ViewHolder(binding.root)

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
        val medical = list[position]
        holder.binding.apply {

        }
    }
}