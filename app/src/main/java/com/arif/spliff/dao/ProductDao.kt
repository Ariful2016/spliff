package com.arif.spliff.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arif.spliff.entity.Product


@Dao
interface ProductDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: Product)

    @Query("SELECT * FROM product")
    fun getAll(): LiveData<List<Product>>

    @Query("SELECT * FROM product WHERE id=:id ")
    fun loadSingle(id: String): Product

    @Query("SELECT * FROM product WHERE category LIKE :qry OR title LIKE :qry OR price LIKE :qry")
    suspend fun loadQueryProducts(qry: String): List<Product>
/*
 title=:qry OR price=:qry OR description=:qry OR category=:qry
 */

}