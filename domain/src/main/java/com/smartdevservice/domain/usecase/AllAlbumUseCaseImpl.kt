package com.smartdevservice.domain.usecase

import com.smartdevservice.domain.Result
import com.smartdevservice.domain.model.AllAlbumResponse
import com.smartdevservice.domain.repository.AlbumRepository

class AllAlbumUseCaseImpl(private val albumRepository: AlbumRepository) : AllAlbumUseCase {

    override suspend fun invoke(): Result<AllAlbumResponse> {
        return albumRepository.loadingAllAlbum()
    }

}