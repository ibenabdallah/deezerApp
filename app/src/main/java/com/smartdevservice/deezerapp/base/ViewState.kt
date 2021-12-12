package com.smartdevservice.deezerapp.base

sealed class ViewState <out T : Any>
data class LoadingState <out T : Any>(val data: T? = null) : ViewState<T>()
data class SuccessState <out T : Any>(val data: T?) : ViewState<T>()
data class FailureState <out T : Any>(val error: Throwable) : ViewState<T>()
data class NoInternetState <T : Any>(val data: T? = null) : ViewState<T>()