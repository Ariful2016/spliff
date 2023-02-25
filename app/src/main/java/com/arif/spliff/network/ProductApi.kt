package com.arif.spliff.network

import com.arif.spliff.model.product.ResponseProductItem
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {

    @GET("products")
   suspend fun getProduct(
    ): Response<List<ResponseProductItem>>
}