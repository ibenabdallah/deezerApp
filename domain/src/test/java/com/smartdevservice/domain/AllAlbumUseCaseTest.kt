package com.smartdevservice.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.smartdevservice.domain.UtilsTest.httpError
import com.smartdevservice.domain.UtilsTest.successAllAlbumResponse
import com.smartdevservice.domain.model.AllAlbumResponse
import com.smartdevservice.domain.repository.AlbumRepository
import com.smartdevservice.domain.usecase.AllAlbumUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class AllAlbumUseCaseTest {

    private val albumRepository: AlbumRepository = mock()

    private val allAlbumUseCase by lazy { AllAlbumUseCaseImpl(albumRepository) }

    @Test
    fun `test AllAlbumUseCase calls AlbumRepository`() {
        runBlocking {
            allAlbumUseCase()
            verify(albumRepository).loadingAllAlbum()
        }
    }

    @Test
    fun `test allAlbumUseCase success`() {
        runBlocking {

            whenever(albumRepository.loadingAllAlbum()).thenReturn(Success(successAllAlbumResponse))

            var responseSuccess: AllAlbumResponse? = null
            var responseFailure: HttpError? = null

            allAlbumUseCase()
                .onSuccess { responseSuccess = it }
                .onFailure { responseFailure = it }

            Assert.assertNotNull(responseSuccess)
            assertEquals(responseSuccess, successAllAlbumResponse)
            assertEquals(responseSuccess?.data, successAllAlbumResponse.data)
            assertEquals(responseSuccess?.data?.size, successAllAlbumResponse.data.size)
            assertEquals(responseSuccess?.data?.get(0), successAllAlbumResponse.data[0])
            assertEquals(responseSuccess?.data?.get(0)?.artist, successAllAlbumResponse.data[0].artist)

            /* Failure */
            Assert.assertNull(responseFailure?.errorCode)
            Assert.assertNull(responseFailure?.throwable)
        }
    }

    @Test
    fun `test allAlbumUseCase failure`() {
        runBlocking {

            whenever(albumRepository.loadingAllAlbum()).thenReturn(Failure(httpError))

            var responseSuccess: AllAlbumResponse? = null
            var responseFailure: HttpError? = null

            allAlbumUseCase()
                .onSuccess { responseSuccess = it }
                .onFailure { responseFailure = it }

            Assert.assertNull(responseSuccess)

            /* Failure */
            assertEquals(responseFailure, httpError)
            assertEquals(responseFailure?.errorCode, httpError.errorCode)
            assertEquals(responseFailure?.throwable, httpError.throwable)
            assertEquals(responseFailure?.throwable?.message, httpError.throwable.message)
        }
    }
}
