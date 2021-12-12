package com.smartdevservice.deezerapp.common

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smartdevservice.data.common.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

inline fun <T> LiveData<T>.subscribe(owner: LifecycleOwner, crossinline onDataReceived: (T) -> Unit) =
    observe(owner, { onDataReceived(it) })

inline fun ViewModel.launch(coroutineContext: CoroutineContext = CoroutineContextProvider().main, crossinline block: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch(coroutineContext) {
        block()
    }
}

inline fun View.onClick(crossinline onClick: () -> Unit) {
    setOnClickListener { onClick() }
}