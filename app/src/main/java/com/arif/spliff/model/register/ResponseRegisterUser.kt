package com.arif.spliff.model.register


import com.google.gson.annotations.SerializedName

data class ResponseRegisterUser(
    @SerializedName("data")
    val data: Data? = null,
    @SerializedName("key")
    val key: String? = null,
    @SerializedName("message")
    val message: Message? = null,
    @SerializedName("success")
    val success: Boolean? = null
)