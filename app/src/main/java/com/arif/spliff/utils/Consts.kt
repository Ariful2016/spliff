package com.arif.spliff.utils

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

const val PRODUCT_BASE_URL = "https://fakestoreapi.com/"
const val AUTH_BASE_URL = "http://note3.itmedicus.org/api/"
const val P_Auth = "P4t13nt4idu53r"


private fun isConnected(context: Context): Boolean {
    var connected = false
    try {
        val cm =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nInfo = cm.activeNetworkInfo
        connected = nInfo != null && nInfo.isAvailable && nInfo.isConnected

        return connected
    } catch (e: Exception) {
        Log.e("Connectivity Exception", e.message!!)
    }
    return connected
}