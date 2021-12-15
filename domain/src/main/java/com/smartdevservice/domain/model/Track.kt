package com.smartdevservice.domain.model

data class Track(
    val id: Long,
    val readable: Boolean,
    val title: String,
    val title_short: String,
    val title_version: String,
    val isrc: String,
    val link: String,
    val duration: Long,
    val track_position: Int,
    val disk_number: Int,
    val rank: Long,
    val explicit_lyrics: Boolean,
    val explicit_content_lyrics: Int,
    val explicit_content_cover: Int,
    val preview: String,
    val md5_image: String,
    val artist: Artist,
    val type: String
)
