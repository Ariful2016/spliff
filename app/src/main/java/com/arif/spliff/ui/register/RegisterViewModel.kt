package com.arif.spliff.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arif.spliff.model.register.RequestRegisterUser
import com.arif.spliff.model.register.ResponseRegisterUser
import com.arif.spliff.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(var repository: Repository) : ViewModel() {


    private var _responseData = MutableLiveData<ResponseRegisterUser>()
    val responseData: LiveData<ResponseRegisterUser>
        get() = _responseData


    fun registerUserVM(user: RequestRegisterUser) {

        val response = repository.registerUserRepo(user)

        response.enqueue(object : Callback<ResponseRegisterUser> {
            override fun onResponse(
                call: Call<ResponseRegisterUser>,
                response: Response<ResponseRegisterUser>
            ) {

                if (response.isSuccessful) {

                    _responseData.postValue(response.body())

                }

            }

            override fun onFailure(call: Call<ResponseRegisterUser>, t: Throwable) {

            }
        })


    }


}