package com.arif.spliff.repo

import com.arif.spliff.dao.CartDao
import com.arif.spliff.dao.ProductDao
import com.arif.spliff.entity.Cart
import com.arif.spliff.entity.Product
import com.arif.spliff.model.login.RequestLogin
import com.arif.spliff.model.product.ResponseProductItem
import com.arif.spliff.model.register.RequestRegisterUser
import com.arif.spliff.network.AuthApi
import com.arif.spliff.network.ProductApi
import javax.inject.Inject

class Repository @Inject constructor(
    private var api: AuthApi,
    private var productApi: ProductApi,
    private var productDao: ProductDao,
    private var cartDao: CartDao,

    ) {


    fun registerUserRepo(user: RequestRegisterUser) = api.registerUser(user)
    fun loginUserRepo(user: RequestLogin) = api.loginUser(user)
    fun logoutUserRepo() = api.logOutUser()

    suspend fun productCache() {
        val response = productApi.getProduct()

        if (response.isSuccessful) {
            response.body()?.forEach {

                val product = responseToProductEntity(it)
                productDao.insertProduct(product)
            }


        }

    }


    fun getProductRepo() = productDao.getAll()
    fun getProductSingleRepo(id: String) = productDao.loadSingle(id)
    suspend fun getQueryProducts(qry: String) = productDao.loadQueryProducts(qry)
    fun getCartRepo() = cartDao.getAllCart()
    fun insertCartRepo(cart: Cart) = cartDao.insertCart(cart)


    private fun responseToProductEntity(item: ResponseProductItem): Product {

        return Product(
            item.id!!,
            item.title!!,
            item.price!!,
            item.description!!,
            item.category!!,
            item.image!!
        )
    }


}