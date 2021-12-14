package com.smartdevservice.data.network

import com.smartdevservice.domain.Failure
import com.smartdevservice.domain.HttpError
import com.smartdevservice.domain.Success
import com.smartdevservice.domain.Result
import retrofit2.Response
import java.io.IOException

const val GENERAL_NETWORK_ERROR_MSG = "Something went wrong, please try again."
const val GENERAL_NETWORK_ERROR_CODE = 400

val globalNetworkError = HttpError(Throwable(GENERAL_NETWORK_ERROR_MSG), GENERAL_NETWORK_ERROR_CODE)

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful) body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (HttpError) -> Unit) {
    if (!isSuccessful) errorBody()?.run { action(HttpError(Throwable(message()), code())) }
}


/**
 * Use this when communicating only with the api service
 */
fun <T : Any> Response<T>.getData(): Result<T> {
    try {
        onSuccess { return Success(it) }
        onFailure { return Failure(it) }
        return Failure(globalNetworkError)
    } catch (e: IOException) {
        return Failure(globalNetworkError)
    }
}