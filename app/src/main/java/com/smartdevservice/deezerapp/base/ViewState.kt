package com.smartdevservice.deezerapp.base

import com.smartdevservice.domain.HttpError

sealed class ViewState <out T : Any>
object LoadingState : ViewState<Nothing>()
data class SuccessState <out T : Any>(val data: T?) : ViewState<T>()
data class FailureState <out T : Any>(val httpError: HttpError) : ViewState<T>()
object NoNetworkState : ViewState<Nothing>()