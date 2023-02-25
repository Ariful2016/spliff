package com.arif.spliff.network

import com.arif.spliff.model.login.RequestLogin
import com.arif.spliff.model.login.ResponseLogin
import com.arif.spliff.model.register.RequestRegisterUser
import com.arif.spliff.model.register.ResponseRegisterUser
import com.arif.spliff.utils.P_Auth
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {

    @POST("register")
    fun registerUser(
        @Body user: RequestRegisterUser,
        @Header("P-Auth-Token") authorization: String = P_Auth
    ): Call<ResponseRegisterUser>

    @POST("login")
    fun loginUser(
        @Body user: RequestLogin,
        @Header("P-Auth-Token") authorization: String = P_Auth
    ): Call<ResponseLogin>

    @GET("logout")
    fun logOutUser(
        @Header("P-Auth-Token") authorization: String = P_Auth
    ): Call<ResponseBody>

}