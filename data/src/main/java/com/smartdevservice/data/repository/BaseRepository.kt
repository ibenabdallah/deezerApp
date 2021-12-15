package com.smartdevservice.data.repository

import com.smartdevservice.data.common.Connectivity
import com.smartdevservice.data.common.CoroutineContextProvider
import com.smartdevservice.domain.*
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BaseRepository(val connectivity: Connectivity) : KoinComponent {

    val contextProvider: CoroutineContextProvider by inject()
    /**
     * Use this when communicating only with the api service
     */
    protected suspend inline fun <T : Any> fetchData(crossinline apiDataProvider: suspend () -> Result<T>): Result<T> {

        return if (connectivity.hasNetworkAccess()) {
            withContext(contextProvider.io) {
                apiDataProvider()
            }
        } else {
            NoNetwork
        }
    }
}