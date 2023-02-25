package com.arif.spliff.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arif.spliff.model.login.RequestLogin
import com.arif.spliff.model.login.ResponseLogin
import com.arif.spliff.model.register.RequestRegisterUser
import com.arif.spliff.model.register.ResponseRegisterUser
import com.arif.spliff.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(var repository: Repository) : ViewModel() {


    private var _responseData = MutableLiveData<ResponseLogin>()
    val responseLoginData: LiveData<ResponseLogin>
        get() = _responseData


    fun loginUserVM(user: RequestLogin) {

        val response = repository.loginUserRepo(user)

        response.enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(
                call: Call<ResponseLogin>,
                response: Response<ResponseLogin>
            ) {

                if (response.isSuccessful) {

                    _responseData.postValue(response.body())

                }

            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {

            }
        })


    }


}