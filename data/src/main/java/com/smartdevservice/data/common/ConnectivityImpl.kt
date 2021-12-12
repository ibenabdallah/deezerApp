package com.smartdevservice.data.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class ConnectivityImpl(private val context: Context) : Connectivity {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun hasNetworkAccess(): Boolean {
        return isWifi() || isMobile()
    }

    override fun isWifi(): Boolean {
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
            return hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
        }
        return false
    }

    override fun isMobile(): Boolean {
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
            return hasTransport(
                NetworkCapabilities.TRANSPORT_CELLULAR
            )
        }
        return false
    }

    override fun getConnectionType(): Int {
        return when {
            isMobile() -> {
                Connectivity.MOBILE
            }
            isWifi() -> {
                Connectivity.WIFI
            }
            else -> {
                Connectivity.NONE
            }
        }
    }
}