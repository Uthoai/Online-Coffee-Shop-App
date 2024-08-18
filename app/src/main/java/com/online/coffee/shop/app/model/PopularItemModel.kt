package com.online.coffee.shop.app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PopularItemModel(
    val title: String = "",
    val description: String = "",
    val picUrl: String = "",
    val price: Double = 0.0,
    val rating: Double = 0.0,
    val extra: String = ""
) : Parcelable
