package com.arif.spliff.model.login


import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("body")
    val body: List<String?>? = null
)