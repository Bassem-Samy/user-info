package com.concept.user.util

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager


interface NetworkStateHelper {
    fun hasInternet(): Boolean
}

class NetworkStateHelperImpl(private val application: Application) : NetworkStateHelper {
    override fun hasInternet(): Boolean {
        val connectivityManager = application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo?.isConnected ?: false

    }

}