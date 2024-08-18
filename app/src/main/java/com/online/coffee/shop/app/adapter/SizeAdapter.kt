package com.online.coffee.shop.app.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.online.coffee.shop.app.R
import com.online.coffee.shop.app.databinding.CoffeeSizeItemLayoutBinding

class SizeAdapter(val item: MutableList<String>) : RecyclerView.Adapter<SizeAdapter.SizeViewHolder>() {
    private lateinit var context: Context
    private var selectedItem = -1
    private var lastSelectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        context = parent.context
        val binding = CoffeeSizeItemLayoutBinding.inflate(
            LayoutInflater.from(context), parent, false
        )
        return SizeViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: SizeViewHolder, @SuppressLint("RecyclerView") position: Int) {
        item[position].let{
            holder.binding.root.setOnClickListener {
                lastSelectedItem = selectedItem
                selectedItem = position
                notifyItemChanged(lastSelectedItem)
                notifyItemChanged(selectedItem)
            }

            if (selectedItem == position) {
                holder.binding.ivCoffeeSize.setBackgroundResource(R.drawable.orange_bg)
            } else {
                holder.binding.ivCoffeeSize.setBackgroundResource(R.drawable.edittext_bg)
            }

            val imageSize = when(position) {
                0-> 45.dpToPx(context)
                1-> 50.dpToPx(context)
                2-> 55.dpToPx(context)
                3-> 65.dpToPx(context)
                else -> 70.dpToPx(context)
            }
            val layoutParams = holder.binding.ivCoffeeSize.layoutParams
            layoutParams.width = imageSize
            layoutParams.height = imageSize
            holder.binding.ivCoffeeSize.layoutParams = layoutParams
        }
    }

    private fun Int.dpToPx(context: Context): Int{
        return (this * context.resources.displayMetrics.density).toInt()
    }

    inner class SizeViewHolder(val binding: CoffeeSizeItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}