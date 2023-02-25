package com.arif.spliff.model.register


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("body")
    val body: List<String?>? = null,
    @SerializedName("email")
    val email: List<String?>? = null,
    @SerializedName("name")
    val name: List<String?>? = null,
    @SerializedName("password")
    val password: List<String?>? = null,
    @SerializedName("password_confirmation")
    val passwordConfirmation: List<String?>? = null,
    @SerializedName("phone")
    val phone: List<String?>? = null
)