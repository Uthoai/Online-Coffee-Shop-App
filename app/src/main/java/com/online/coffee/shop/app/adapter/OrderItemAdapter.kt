package com.online.coffee.shop.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.online.coffee.shop.app.databinding.OrderItemLayoutBinding
import com.online.coffee.shop.app.helper.ChangeNumberItemsListener
import com.online.coffee.shop.app.helper.ManagementCart
import com.online.coffee.shop.app.model.ItemsModel

class OrderItemAdapter(
    private val itemList: ArrayList<ItemsModel>,
    context: Context,
    private val changeNumberItemsListener: ChangeNumberItemsListener? = null
) : RecyclerView.Adapter<OrderItemAdapter.OrderItemViewHolder>() {
    private val managementCart = ManagementCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        val binding = OrderItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        itemList[position].let {
            holder.binding.apply {
                tvTitle.text = it.title
                tvUnitPrice.text = "$${it.price}"
                tvQuantity.text = "$${it.numberInCart}"
                tvTotalPriceOfItem.text = "$${Math.round(it.numberInCart * it.price)}"

                Glide.with(holder.itemView.context)
                    .load(it.picUrl)
                    .apply(RequestOptions().transform(CenterCrop()))
                    .into(picCart)

                btnIncrementQuantity.setOnClickListener {
                    managementCart.plusItem(itemList, position,object : ChangeNumberItemsListener {
                        override fun onChanged() {
                            notifyDataSetChanged()
                            changeNumberItemsListener?.onChanged()
                        }

                    })
                    //changeNumberItemsListener?.onChanged()
                }
                btnDecrementQuantity.setOnClickListener {
                    managementCart.minusItem(itemList, position,object : ChangeNumberItemsListener {
                        override fun onChanged() {
                            notifyDataSetChanged()
                            changeNumberItemsListener?.onChanged()
                        }
                    })
                }
            }
        }
    }

    inner class OrderItemViewHolder(val binding: OrderItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}