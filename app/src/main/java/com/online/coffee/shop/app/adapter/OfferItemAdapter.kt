package com.online.coffee.shop.app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.online.coffee.shop.app.databinding.OfferItemLayoutBinding
import com.online.coffee.shop.app.model.OfferModel

class OfferItemAdapter(private val offerList: List<OfferModel>) :
    RecyclerView.Adapter<OfferItemAdapter.OfferViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = OfferItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return OfferViewHolder(binding)
    }

    override fun getItemCount(): Int = offerList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        offerList[position].let {
            holder.binding.apply {
                tvItemTitle.text = it.title
                tvItemPrice.text = "$"+it.price.toString()

                Glide.with(holder.itemView.context)
                    .load(it.picUrl)
                    .into(holder.binding.ivItemImage)
            }
        }

    }

    inner class OfferViewHolder(val binding: OfferItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}