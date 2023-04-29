package com.homairaahmed.bddoctorhub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.homairaahmed.bddoctorhub.data.Category
import com.homairaahmed.bddoctorhub.databinding.CategoryLayoutBinding
import com.homairaahmed.bddoctorhub.utils.ImageUtils

class CategoryAdapter(private val categoryList : List<Category>, private val context : Context) : Adapter<CategoryAdapter.CategoryViewHolder>() {


    private val diffUtil = object : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.catId == newItem.catId
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, diffUtil)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
       val binding = CategoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]
        holder.binding.apply {
            tvCategoryName.text = category.catName
            ImageUtils.showNetworkImage(ivCategoryImage,context,category.catImage)
        }

    }

    inner class CategoryViewHolder(val binding : CategoryLayoutBinding) : RecyclerView.ViewHolder(binding.root)

}

