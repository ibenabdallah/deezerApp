package com.smartdevservice.domain.usecase

import com.smartdevservice.domain.Result
import com.smartdevservice.domain.model.DetailsAlbumResponse
import com.smartdevservice.domain.repository.AlbumRepository

class DetailsAlbumUseCaseImpl(private val albumRepository: AlbumRepository) : DetailsAlbumUseCase {

    override suspend fun invoke(url: String): Result<DetailsAlbumResponse> {
        return albumRepository.loadingDetailsAlbum(url)
    }

}