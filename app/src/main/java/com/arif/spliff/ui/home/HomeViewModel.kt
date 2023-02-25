package com.arif.spliff.ui.home

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
class HomeViewModel @Inject constructor(var repository: Repository) : ViewModel() {

    private var _queryData = MutableLiveData<List<Product>>()
    val queryData: LiveData<List<Product>>
        get() = _queryData


    fun responseProductSingle(qry: String) {

        viewModelScope.launch {
            _queryData.postValue(repository.getQueryProducts(qry))
        }

    }


}