package com.smartdevservice.deezerapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smartdevservice.deezerapp.base.*
import com.smartdevservice.domain.model.Album
import com.smartdevservice.domain.model.Track
import com.smartdevservice.domain.noNetwork
import com.smartdevservice.domain.onFailure
import com.smartdevservice.domain.onSuccess
import com.smartdevservice.domain.usecase.AllAlbumUseCase
import com.smartdevservice.domain.usecase.DetailsAlbumUseCase

class AlbumViewModel(
    private val allAlbumUseCase: AllAlbumUseCase,
    private val detailsAlbumUseCase: DetailsAlbumUseCase
) : BaseViewModel() {

    private val _albumsState = MutableLiveData<ViewState<ArrayList<Album>>>()
    val albumsState: LiveData<ViewState<ArrayList<Album>>>
        get() = _albumsState

    private val _detailsState = MutableLiveData<ViewState<ArrayList<Track>>>()
    val detailsState: LiveData<ViewState<ArrayList<Track>>>
        get() = _detailsState

    fun loadingAllAlbum() = executeUseCase {
        _albumsState.value = LoadingState
        allAlbumUseCase()
            .onSuccess { _albumsState.value = SuccessState(it.data) }
            .onFailure { _albumsState.value = FailureState(it) }
            .noNetwork { _albumsState.value = NoNetworkState }
    }

    fun loadingDetailsAlbum(url: String) = executeUseCase {
        _detailsState.value = LoadingState
        detailsAlbumUseCase(url)
            .onSuccess { _detailsState.value = SuccessState(it.data) }
            .onFailure { _detailsState.value = FailureState(it) }
            .noNetwork { _detailsState.value = NoNetworkState }
    }
}