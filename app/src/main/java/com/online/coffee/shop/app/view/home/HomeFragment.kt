package com.online.coffee.shop.app.view.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.online.coffee.shop.app.adapter.CategoryAdapter
import com.online.coffee.shop.app.adapter.OfferItemAdapter
import com.online.coffee.shop.app.adapter.PopularItemAdapter
import com.online.coffee.shop.app.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var popularItemAdapter: PopularItemAdapter
    private lateinit var offerItemAdapter: OfferItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        // Set up RecyclerView for categories
        binding.recyclerViewCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        homeViewModel.categories.observe(viewLifecycleOwner) { categories ->
            categoryAdapter = CategoryAdapter(categories)
            binding.recyclerViewCategory.adapter = categoryAdapter
            binding.progressBarCategory.visibility = View.GONE
        }

        // Set up RecyclerView for popular items
        binding.recyclerViewPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        homeViewModel.popularItems.observe(viewLifecycleOwner) { popularItems ->
            popularItemAdapter = PopularItemAdapter(popularItems)
            binding.recyclerViewPopular.adapter = popularItemAdapter
            binding.progressBarPopular.visibility = View.GONE
        }

        // Set up RecyclerView for offers
        binding.recyclerViewOffer.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        homeViewModel.offers.observe(viewLifecycleOwner) { offers ->
            offerItemAdapter = OfferItemAdapter(offers)
            binding.recyclerViewOffer.adapter = offerItemAdapter
            binding.progressBarOffer.visibility = View.GONE
        }

    }
}