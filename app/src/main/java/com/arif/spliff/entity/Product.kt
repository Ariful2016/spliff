package com.arif.spliff.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "product")
data class Product(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String
) : Parcelable
