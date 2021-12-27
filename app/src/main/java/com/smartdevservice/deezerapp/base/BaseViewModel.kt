package com.smartdevservice.deezerapp.base

import androidx.lifecycle.ViewModel
import com.smartdevservice.deezerapp.common.launch
import org.koin.core.component.KoinComponent

open class BaseViewModel : ViewModel(), KoinComponent {

    protected fun executeUseCase(action: suspend () -> Unit) {
        launch { action() }
    }
}