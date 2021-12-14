package com.smartdevservice.domain

sealed class Result<out T>
data class Success<out T>(val data: T) : Result<T>()
data class Failure(val httpError: HttpError) : Result<Nothing>()
object NoNetwork : Result<Nothing>()

class HttpError(val throwable: Throwable, val errorCode: Int = 0)

inline fun <T : Any> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Success) action(data)
    return this
}

inline fun <T : Any> Result<T>.onFailure(action: (HttpError) -> Unit) : Result<T> {
    if (this is Failure) action(httpError)
    return this
}

inline fun <T : Any> Result<T>.noNetwork(action: () -> Unit) {
    if (this is NoNetwork) action()
}
