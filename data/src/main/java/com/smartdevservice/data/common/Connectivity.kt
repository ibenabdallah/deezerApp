package com.smartdevservice.data.common

import android.net.NetworkCapabilities

interface Connectivity {

    fun hasNetworkAccess(): Boolean

    fun isWifi() : Boolean

    fun isMobile() : Boolean
}