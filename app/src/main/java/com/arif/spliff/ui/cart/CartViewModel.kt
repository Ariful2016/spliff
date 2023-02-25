package com.arif.spliff.ui.cart

import androidx.lifecycle.ViewModel
import com.arif.spliff.entity.Cart
import com.arif.spliff.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var repository: Repository) : ViewModel() {


    fun responseAllCart() = repository.getCartRepo()
    fun insertCart(cart: Cart) = repository.insertCartRepo(cart)
    fun responseProductSingle(id: String) = repository.getProductSingleRepo(id)


}