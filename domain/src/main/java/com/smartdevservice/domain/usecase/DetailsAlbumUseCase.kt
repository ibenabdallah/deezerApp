package com.smartdevservice.domain.usecase

import com.smartdevservice.domain.Result
import com.smartdevservice.domain.model.AllAlbumResponse
import com.smartdevservice.domain.model.DetailsAlbumResponse

interface DetailsAlbumUseCase {
    suspend operator fun invoke(url: String): Result<DetailsAlbumResponse>
}