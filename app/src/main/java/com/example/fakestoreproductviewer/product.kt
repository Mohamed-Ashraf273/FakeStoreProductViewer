package com.example.fakestoreproductviewer

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Product(
    val id: Int,
    val title: String,
    val price: Int,
    val description: String,
    val images: List<String>
) : Parcelable