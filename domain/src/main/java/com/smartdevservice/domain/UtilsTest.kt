package com.smartdevservice.domain

import com.smartdevservice.domain.model.*

object UtilsTest {

    const val urlIdAlbum = "1581964/tracks"
    const val FAKE_FAILURE_ERROR_CODE = 400
    const val FAKE_FAILURE_ERROR_MSG = "Response.error()"
    val fakeHttpError = HttpError(Throwable(FAKE_FAILURE_ERROR_MSG), FAKE_FAILURE_ERROR_CODE)

    private val error = Error("DataException", "no data",800)
    private val artistDetails = ArtistDetails(123, "", "", "", "", "", "", "", "")
    val album = Album(1, "","","","","", "", "", "", 10,
        "", "", true,"", true, 1587418247, artistDetails, "")
    val successAllAlbumResponse = AllAlbumResponse(arrayListOf(album), null)
    val errorAllAlbumResponse = AllAlbumResponse(null, error)


    private val artist = Artist(123, "", "", "")
    val track = Track(1, true, "", "", "", "", "", 123, 45, 5, 100,
        true, 1, 1, "", "", artist, "")
    val successDetailsAlbumResponse = DetailsAlbumResponse(arrayListOf(track), null)

    val errorDetailsAlbumResponse = DetailsAlbumResponse(null, error)
}