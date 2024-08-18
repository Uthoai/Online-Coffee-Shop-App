package com.online.coffee.shop.app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.online.coffee.shop.app.R
import com.online.coffee.shop.app.databinding.CategoryItemLayoutBinding
import com.online.coffee.shop.app.model.CategoryModel

class CategoryAdapter(private val categoryList: List<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedItem = -1
    private var lastSelectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            CategoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, @SuppressLint("RecyclerView") position: Int) {
        categoryList[position].let {
            holder.binding.textCategory.text = it.title

            holder.binding.root.setOnClickListener {
                lastSelectedItem = selectedItem
                selectedItem = position
                notifyItemChanged(lastSelectedItem)
                notifyItemChanged(selectedItem)
            }

            if (selectedItem == position) {
                holder.binding.root.setBackgroundResource(R.drawable.orange_bg)
            } else{
                holder.binding.root.setBackgroundResource(R.drawable.edittext_bg)
            }
        }
    }

    inner class CategoryViewHolder(val binding: CategoryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}