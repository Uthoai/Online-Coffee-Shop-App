package com.online.coffee.shop.app.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.online.coffee.shop.app.R
import com.online.coffee.shop.app.adapter.CategoryAdapter
import com.online.coffee.shop.app.base.BaseActivity
import com.online.coffee.shop.app.databinding.ActivityMainBinding
import com.online.coffee.shop.app.view.home.HomeViewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    /*private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*val recyclerView: RecyclerView = findViewById(R.id.recyclerViewCategory)
        recyclerView.layoutManager = LinearLayoutManager(this)

        homeViewModel.categories.observe(this, Observer { categories ->
            categoryAdapter = CategoryAdapter(categories)
            recyclerView.adapter = categoryAdapter
        })*/
    }
}