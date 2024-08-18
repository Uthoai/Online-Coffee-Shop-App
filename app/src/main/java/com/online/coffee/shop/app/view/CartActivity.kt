package com.online.coffee.shop.app.view

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.online.coffee.shop.app.adapter.OrderItemAdapter
import com.online.coffee.shop.app.base.BaseActivity
import com.online.coffee.shop.app.databinding.ActivityCartBinding
import com.online.coffee.shop.app.helper.ChangeNumberItemsListener
import com.online.coffee.shop.app.helper.ManagementCart

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managementCart: ManagementCart
    private lateinit var orderItemAdapter: OrderItemAdapter

    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managementCart = ManagementCart(this)

        calculateCart()
        setRecyclerView()
        setListener()

    }


    private fun setRecyclerView() {
        binding.recyclerViewOrderItem.layoutManager =
            LinearLayoutManager(this@CartActivity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewOrderItem.adapter = OrderItemAdapter(
            managementCart.getListCart(),
            this,
            object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()
                }
            })
    }

    private fun setListener() {
        binding.btnProceedCheckout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 15.0
        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100) / 100.0
        val total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100) / 100.0
        val itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100.0

        with(binding) {
            tvSubtotalPrice.text = "$$itemTotal"
            tvTotalTaxPrice.text = "$$tax"
            tvDeliveryPrice.text = "$$delivery"
            tvTotalPrice.text = "$$total"
        }


    }
}