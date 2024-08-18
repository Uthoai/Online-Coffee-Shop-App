package com.online.coffee.shop.app.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.online.coffee.shop.app.databinding.PopularItemLayoutBinding
import com.online.coffee.shop.app.model.ItemsModel
import com.online.coffee.shop.app.view.DetailActivity

class PopularItemAdapter(private var itemList: List<ItemsModel>) :
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
        itemList[position].let {item->
            holder.binding.apply {
                tvItemTitle.text = item.title
                tvExtraText.text = item.extra
                tvItemPrice.text = "$"+item.price.toString()
                ratingBar.rating = item.rating.toFloat()
                Glide.with(holder.itemView.context)
                    .load(item.picUrl)  // Use picUrl here
                    .into(holder.binding.ivItemImage)
            }
            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, DetailActivity::class.java)
                intent.putExtra("item", item)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    inner class PopularItemViewHolder(val binding: PopularItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}