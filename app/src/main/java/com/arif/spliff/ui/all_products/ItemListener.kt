package com.arif.spliff.ui.all_products

import com.arif.spliff.entity.Product

interface ItemListener {
    fun addToCart(product: Product)

}