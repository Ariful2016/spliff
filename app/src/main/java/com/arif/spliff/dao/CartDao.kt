package com.arif.spliff.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arif.spliff.entity.Cart


@Dao
interface CartDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCart(cart: Cart)

    @Query("SELECT * FROM cart")
    fun getAllCart(): LiveData<List<Cart>>


}