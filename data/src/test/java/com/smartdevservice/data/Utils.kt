package com.smartdevservice.data

import com.smartdevservice.domain.UtilsTest.FAKE_FAILURE_ERROR_MSG
import okhttp3.ResponseBody.Companion.toResponseBody

object Utils {
    val fakeResponseBodyFailure = FAKE_FAILURE_ERROR_MSG.toResponseBody()
}