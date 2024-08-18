package com.online.coffee.shop.app.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.online.coffee.shop.app.adapter.SizeAdapter
import com.online.coffee.shop.app.base.BaseActivity
import com.online.coffee.shop.app.databinding.ActivityDetailBinding
import com.online.coffee.shop.app.helper.ManagementCart
import com.online.coffee.shop.app.model.ItemsModel

class DetailActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private lateinit var managementCart: ManagementCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        item = intent.getParcelableExtra("item")!!

        managementCart = ManagementCart(this)

        setListener()

        initSizeList()
    }

    private fun initSizeList() {
        val listSize = ArrayList<String>()
        listSize.add("1")
        listSize.add("2")
        listSize.add("3")
        listSize.add("5")

        binding.recyclerViewCoffeeSizeList.adapter = SizeAdapter(listSize)
        binding.recyclerViewCoffeeSizeList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    @SuppressLint("SetTextI18n")
    private fun setListener() {
        binding.apply {
            tvTitleSelectedItem.text = item.title
            tvPrice.text = "$" + item.price.toString()
            tvDesc.text = item.description
            ratingBar.rating = item.rating.toFloat()
            Glide.with(this@DetailActivity)
                .load(item.picUrl)  // Use picUrl here
                .into(ivSelectedItemImage)

            btnAddToCart.setOnClickListener {
                item.numberInCart = Integer.valueOf(tvQuantity.text.toString())
                managementCart.insertItems(item)
            }

            btnBack.setOnClickListener {
                startActivity(Intent(this@DetailActivity, MainActivity::class.java))
            }

            btnIncrementQuantity.setOnClickListener {
                tvQuantity.text = (item.numberInCart + 1).toString()
                item.numberInCart++
            }

            btnDecrementQuantity.setOnClickListener {
                if (item.numberInCart > 1) {
                    tvQuantity.text = (item.numberInCart - 1).toString()
                    item.numberInCart--
                }
            }
        }
    }


}