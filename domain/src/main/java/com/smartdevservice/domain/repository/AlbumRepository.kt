package com.smartdevservice.domain.repository

import com.smartdevservice.domain.Result
import com.smartdevservice.domain.model.AllAlbumResponse
import com.smartdevservice.domain.model.DetailsAlbumResponse

interface AlbumRepository {

    suspend fun loadingAllAlbum(): Result<AllAlbumResponse>

    suspend fun loadingDetailsAlbum(url: String): Result<DetailsAlbumResponse>

}