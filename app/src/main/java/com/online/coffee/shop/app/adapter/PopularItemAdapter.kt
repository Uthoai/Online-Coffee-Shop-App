package com.online.coffee.shop.app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.online.coffee.shop.app.R
import com.online.coffee.shop.app.databinding.PopularItemLayoutBinding
import com.online.coffee.shop.app.model.PopularItemModel

class PopularItemAdapter(private var itemList: List<PopularItemModel>) :
    RecyclerView.Adapter<PopularItemAdapter.PopularItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularItemViewHolder {
        val binding = PopularItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PopularItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PopularItemViewHolder, position: Int) {
        itemList[position].let {
            holder.binding.apply {
                tvItemTitle.text = it.title
                tvExtraText.text = it.extra
                tvItemPrice.text = "$"+it.price.toString()
                ratingBar.rating = it.rating.toFloat()
                Glide.with(holder.itemView.context)
                    .load(it.picUrl)  // Use picUrl here
                    .into(holder.binding.ivItemImage)
            }
        }
    }

    inner class PopularItemViewHolder(val binding: PopularItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}