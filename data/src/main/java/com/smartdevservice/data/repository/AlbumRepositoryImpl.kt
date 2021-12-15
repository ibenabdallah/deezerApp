package com.smartdevservice.data.repository

import com.smartdevservice.data.common.Connectivity
import com.smartdevservice.data.network.DeezerApi
import com.smartdevservice.data.network.getData
import com.smartdevservice.domain.Result
import com.smartdevservice.domain.model.AllAlbumResponse
import com.smartdevservice.domain.model.DetailsAlbumResponse

import com.smartdevservice.domain.repository.AlbumRepository

class AlbumRepositoryImpl(private val deezerApi: DeezerApi, connectivity: Connectivity) :
    BaseRepository(connectivity), AlbumRepository {

    override suspend fun loadingAllAlbum(): Result<AllAlbumResponse> {
        return fetchData {
            deezerApi.loadingAllAlbums().getData()
        }
    }

    override suspend fun loadingDetailsAlbum(url: String): Result<DetailsAlbumResponse> {
        return fetchData {
            deezerApi.loadingDetailsAlbum(url).getData()
        }
    }

}