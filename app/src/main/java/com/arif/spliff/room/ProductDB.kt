package com.arif.spliff.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arif.spliff.dao.CartDao
import com.arif.spliff.dao.ProductDao
import com.arif.spliff.entity.Cart
import com.arif.spliff.entity.Product

@Database(entities = [Product::class, Cart::class], version = 1)
abstract class ProductDB : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
    abstract fun getCartDao(): CartDao


}