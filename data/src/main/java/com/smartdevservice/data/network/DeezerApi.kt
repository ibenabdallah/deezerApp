package com.smartdevservice.data.network

import com.smartdevservice.domain.model.AllAlbumResponse
import com.smartdevservice.domain.model.DetailsAlbumResponse
import retrofit2.Response
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Path

interface DeezerApi {

    @GET("user/2529/albums")
    suspend fun loadingAllAlbums() : Response<AllAlbumResponse>

    @GET("album/{url}")
    suspend fun loadingDetailsAlbum(@Path("url" , encoded = true) url: String) : Response<DetailsAlbumResponse>

}