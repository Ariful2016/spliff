package com.arif.spliff.ui.all_products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arif.spliff.entity.Cart
import com.arif.spliff.entity.Product
import com.arif.spliff.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(var repository: Repository) : ViewModel() {


    fun responseAllProduct() = repository.getProductRepo()
    fun responseAllCart() = repository.getCartRepo()
    fun insertCart(cart: Cart) = repository.insertCartRepo(cart)

    private var _queryData = MutableLiveData<List<Product>>()
    val queryData: LiveData<List<Product>>
        get() = _queryData


    fun responseProductSingle(qry: String) {

        viewModelScope.launch {
            _queryData.postValue(repository.getQueryProducts(qry))
        }

    }


}