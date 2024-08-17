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

class HomeViewModel: ViewModel() {

    private val _categories = MutableLiveData<List<CategoryModel>>()
    val categories: LiveData<List<CategoryModel>> get() = _categories

    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("Category")

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val categoryList = mutableListOf<CategoryModel>()
                for (categorySnapshot in snapshot.children) {
                    val category = categorySnapshot.getValue(CategoryModel::class.java)
                    if (category != null) {
                        categoryList.add(category)
                    }
                }
                _categories.value = categoryList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors.
            }
        })
    }
}
