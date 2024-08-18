package com.online.coffee.shop.app.view.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.online.coffee.shop.app.model.CategoryModel
import com.online.coffee.shop.app.model.OfferModel
import com.online.coffee.shop.app.model.ItemsModel

class HomeViewModel: ViewModel() {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> get() = _categories

    private val _popularItems = MutableLiveData<List<ItemsModel>>()
    val popularItems: LiveData<List<ItemsModel>> get() = _popularItems

    private val _offers = MutableLiveData<List<OfferModel>>()
    val offers: LiveData<List<OfferModel>> get() = _offers

    init {
        fetchCategories()
        fetchPopularItems()
        fetchOffers()
    }

    private fun fetchCategories() {
        databaseReference.child("Category").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val categoryList = mutableListOf<CategoryModel>()
                for (categorySnapshot in snapshot.children) {
                    val category = categorySnapshot.getValue(CategoryModel::class.java)
                    category?.let { categoryList.add(it) }
                }
                _categories.value = categoryList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    private fun fetchPopularItems() {
        databaseReference.child("Items").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemList = mutableListOf<ItemsModel>()
                for (itemSnapshot in snapshot.children) {
                    val item = itemSnapshot.getValue(ItemsModel::class.java)
                    item?.let { itemList.add(it) }
                }
                _popularItems.value = itemList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    private fun fetchOffers() {
        databaseReference.child("Offers").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val offerList = mutableListOf<OfferModel>()
                for (offerSnapshot in snapshot.children) {
                    val offer = offerSnapshot.getValue(OfferModel::class.java)
                    offer?.let { offerList.add(it) }
                }
                _offers.value = offerList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }


}
