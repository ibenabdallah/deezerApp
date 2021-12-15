package com.smartdevservice.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
open class Artist(
    open val id: Long,
    open val name: String,
    open val tracklist: String,
    open val type: String
) : Parcelable
