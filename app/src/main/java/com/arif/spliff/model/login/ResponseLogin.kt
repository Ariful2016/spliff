package com.arif.spliff.model.login


import com.google.gson.annotations.SerializedName

data class ResponseLogin(
    @SerializedName("data")
    val `data`: Data? = null,
    @SerializedName("message")
    val message: Message? = null,
    @SerializedName("success")
    val success: Boolean? = null
)