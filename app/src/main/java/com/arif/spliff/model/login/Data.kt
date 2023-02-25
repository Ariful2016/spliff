package com.arif.spliff.model.login


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("address")
    val address: String? = null,
    @SerializedName("company")
    val company: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("key")
    val key: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("occupation")
    val occupation: Any? = null,
    @SerializedName("phone")
    val phone: String? = null
)