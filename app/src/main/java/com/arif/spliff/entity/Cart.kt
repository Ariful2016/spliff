package com.arif.spliff.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val productID: Int,
    val noOfProduct: Int
)