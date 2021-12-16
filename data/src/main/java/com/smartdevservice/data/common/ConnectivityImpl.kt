package com.smartdevservice.data.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class ConnectivityImpl(context: Context) : Connectivity {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun hasNetworkAccess(): Boolean {
        return isWifi() || isMobile()
    }

    override fun isWifi(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                return hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return type == ConnectivityManager.TYPE_WIFI
            }
        }
        return false
    }

    override fun isMobile(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.run {
                return hasTransport(
                    NetworkCapabilities.TRANSPORT_CELLULAR
                )
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return type == ConnectivityManager.TYPE_MOBILE
            }
        }
        return false
    }

}