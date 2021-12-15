package com.smartdevservice.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ArtistDetails(
    override val id: Long,
    override val name: String,
    val picture: String,
    val picture_small: String,
    val picture_medium: String,
    val picture_big: String,
    val picture_xl: String,
    override val tracklist: String,
    override val type: String
) : Artist(id, name, tracklist, type),Parcelable
