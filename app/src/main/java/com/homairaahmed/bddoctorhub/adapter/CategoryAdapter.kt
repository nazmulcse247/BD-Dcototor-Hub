package com.homairaahmed.bddoctorhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.homairaahmed.bddoctorhub.data.Category
import com.homairaahmed.bddoctorhub.databinding.CategoryLayoutBinding

class CategoryAdapter(private val categoryList : List<Category>, private val context : Context) : Adapter<CategoryAdapter.CategoryViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
       val binding = CategoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

    }

    inner class CategoryViewHolder(val binding : CategoryLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}

