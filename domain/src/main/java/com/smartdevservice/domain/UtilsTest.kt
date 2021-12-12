package com.smartdevservice.domain

import com.smartdevservice.domain.model.Album
import com.smartdevservice.domain.model.AllAlbumResponse
import com.smartdevservice.domain.model.Artist

object UtilsTest {

    const val FAKE_FAILURE_ERROR_CODE = 400
    const val THROWABLE_MSG = "Response.error()"
    val httpError = HttpError(Throwable(THROWABLE_MSG), FAKE_FAILURE_ERROR_CODE)


    val artist = Artist(123, "", "", "", "", "", "", "", "")
    val album = Album(1, "","","","","", "", "", "", 10,
        "", "", true,"", true, 1587418247, artist, "")
    val successAllAlbumResponse = AllAlbumResponse(arrayListOf(album))
}