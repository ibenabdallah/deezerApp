package com.smartdevservice.data.common

interface Connectivity {

    fun hasNetworkAccess(): Boolean

    fun isWifi() : Boolean

    fun isMobile() : Boolean
}