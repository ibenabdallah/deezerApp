package com.smartdevservice.data

import com.smartdevservice.domain.UtilsTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody

object Utils {
    val failureResponseBody = UtilsTest.FAKE_FAILURE_ERROR_MSG.toResponseBody("text".toMediaTypeOrNull())
}