package com.arif.spliff.entity

import androidx.room.Entity

@Entity(tableName = "cart")
data class CartTemp(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val qty: Double,
    val totalPrice: Double
)