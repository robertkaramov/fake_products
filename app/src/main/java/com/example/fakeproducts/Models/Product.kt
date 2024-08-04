package com.example.fakeproducts.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val images: List<String>,
    val creationAt: String,
    val updatedAt: String,
    val category: Category,
): Parcelable