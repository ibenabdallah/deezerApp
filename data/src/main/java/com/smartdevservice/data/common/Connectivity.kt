package com.smartdevservice.data.common

import android.net.NetworkCapabilities

interface Connectivity {

    fun hasNetworkAccess(): Boolean

    fun isWifi() : Boolean

    fun isMobile() : Boolean

    fun getConnectionType() : Int

    companion object {
        /**
         * connexion wifi
         */
        const val WIFI = NetworkCapabilities.TRANSPORT_WIFI

        /**
         * connexion 3g ou autre
         */
        const val MOBILE = NetworkCapabilities.TRANSPORT_CELLULAR

        const val NONE = -1
    }
}