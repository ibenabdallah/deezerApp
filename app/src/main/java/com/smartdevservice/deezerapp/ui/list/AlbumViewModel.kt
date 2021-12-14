package com.smartdevservice.deezerapp.ui.list

import com.smartdevservice.deezerapp.base.*
import com.smartdevservice.domain.model.Album
import com.smartdevservice.domain.noNetwork
import com.smartdevservice.domain.onFailure
import com.smartdevservice.domain.onSuccess
import com.smartdevservice.domain.usecase.AllAlbumUseCase

class AlbumViewModel(
    private val allAlbumUseCase: AllAlbumUseCase
) : BaseViewModel<ArrayList<Album>>() {

    fun loadingAllAlbum() = executeUseCase {
        _viewState.value = LoadingState
        allAlbumUseCase()
            .onSuccess { _viewState.value = SuccessState(it.data) }
            .onFailure { _viewState.value = FailureState(it) }
            .noNetwork { _viewState.value = NoNetworkState }
    }
}