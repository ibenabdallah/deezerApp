package com.smartdevservice.deezerapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.smartdevservice.data.common.Connectivity
import com.smartdevservice.data.common.CoroutineContextProvider
import com.smartdevservice.deezerapp.common.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BaseViewModel<T : Any>() : ViewModel(), KoinComponent {

    protected val _viewState = MutableLiveData<ViewState<T>>()
    val viewState: LiveData<ViewState<T>>
        get() = _viewState


    protected fun executeUseCase(action: suspend () -> Unit) {
        launch { action() }
    }
}