package com.smartdevservice.data.network

import com.smartdevservice.domain.model.Album
import com.smartdevservice.domain.model.AllAlbumResponse
import retrofit2.Response
import retrofit2.http.GET

interface DeezerApi {

    @GET("albums")
    suspend fun loadingAllAlbums() : Response<AllAlbumResponse>

}