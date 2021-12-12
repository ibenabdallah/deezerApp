package com.smartdevservice.domain.usecase

import com.smartdevservice.domain.Result
import com.smartdevservice.domain.model.AllAlbumResponse

interface AllAlbumUseCase {
    suspend operator fun invoke(): Result<AllAlbumResponse>
}