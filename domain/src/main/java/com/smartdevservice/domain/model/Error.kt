package com.smartdevservice.domain.model

data class Error(
    val type: String,
    val message: String,
    val code: Int
)
