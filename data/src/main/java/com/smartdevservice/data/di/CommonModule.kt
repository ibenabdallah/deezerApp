package com.smartdevservice.data.di


import com.smartdevservice.data.common.Connectivity
import com.smartdevservice.data.common.ConnectivityImpl
import com.smartdevservice.data.common.CoroutineContextProvider
import org.koin.dsl.module


val commonModule = module {

    single { CoroutineContextProvider() }
    single <Connectivity> { ConnectivityImpl(get()) }

}