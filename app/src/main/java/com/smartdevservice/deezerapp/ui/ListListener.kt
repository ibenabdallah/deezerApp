package com.smartdevservice.deezerapp.ui

import com.smartdevservice.domain.model.Album

interface ListListener {
    fun onClickItem(album: Album)
}