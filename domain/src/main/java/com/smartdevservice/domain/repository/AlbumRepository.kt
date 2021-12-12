package com.smartdevservice.domain.repository

import com.smartdevservice.domain.Result
import com.smartdevservice.domain.model.AllAlbumResponse

interface AlbumRepository {

    suspend fun loadingAllAlbum(): Result<AllAlbumResponse>

}