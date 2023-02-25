package com.arif.spliff.ui.all_products

import com.arif.spliff.entity.Product

interface ProductDetailsListener {

    fun sendProduct(product: Product)

}