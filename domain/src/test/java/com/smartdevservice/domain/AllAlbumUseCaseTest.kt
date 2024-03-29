package com.smartdevservice.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.smartdevservice.domain.UtilsTest.errorAllAlbumResponse
import com.smartdevservice.domain.UtilsTest.fakeHttpError
import com.smartdevservice.domain.UtilsTest.successAllAlbumResponse
import com.smartdevservice.domain.model.AllAlbumResponse
import com.smartdevservice.domain.repository.AlbumRepository
import com.smartdevservice.domain.usecase.AllAlbumUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
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
            var responseNoNetwork: NoNetwork? = null

            allAlbumUseCase()
                .onSuccess { responseSuccess = it }
                .onFailure { responseFailure = it }
                .noNetwork { responseNoNetwork = NoNetwork }

            assertNotNull(responseSuccess)
            assertEquals(responseSuccess, successAllAlbumResponse)
            assertEquals(responseSuccess?.data, successAllAlbumResponse.data)
            assertEquals(responseSuccess?.data?.size, successAllAlbumResponse.data?.size)
            assertEquals(responseSuccess?.data?.get(0), successAllAlbumResponse.data?.get(0))
            assertEquals(responseSuccess?.data?.get(0)?.artist, successAllAlbumResponse.data?.get(0)?.artist)

            /* Failure */
            assertNull(responseFailure?.errorCode)
            assertNull(responseFailure?.throwable)

            /* No Network */
            assertNull(responseNoNetwork)
        }
    }

    @Test
    fun `test allAlbumUseCase error no data`() {
        runBlocking {

            whenever(albumRepository.loadingAllAlbum()).thenReturn(Success(errorAllAlbumResponse))

            var responseSuccess: AllAlbumResponse? = null
            var responseFailure: HttpError? = null
            var responseNoNetwork: NoNetwork? = null

            allAlbumUseCase()
                .onSuccess { responseSuccess = it }
                .onFailure { responseFailure = it }
                .noNetwork { responseNoNetwork = NoNetwork }

            assertNotNull(responseSuccess)
            assertEquals(responseSuccess, errorAllAlbumResponse)
            assertNull(responseSuccess?.data)
            assertEquals(responseSuccess?.error, errorAllAlbumResponse.error)
            assertEquals(responseSuccess?.error?.code, errorAllAlbumResponse.error?.code)
            assertEquals(responseSuccess?.error?.message, errorAllAlbumResponse.error?.message)
            assertEquals(responseSuccess?.error?.type, errorAllAlbumResponse.error?.type)

            /* Failure */
            assertNull(responseFailure?.errorCode)
            assertNull(responseFailure?.throwable)

            /* No Network */
            assertNull(responseNoNetwork)
        }
    }

    @Test
    fun `test allAlbumUseCase failure`() {
        runBlocking {

            whenever(albumRepository.loadingAllAlbum()).thenReturn(Failure(fakeHttpError))

            var responseSuccess: AllAlbumResponse? = null
            var responseFailure: HttpError? = null
            var responseNoNetwork: NoNetwork? = null

            allAlbumUseCase()
                .onSuccess { responseSuccess = it }
                .onFailure { responseFailure = it }
                .noNetwork { responseNoNetwork = NoNetwork }

            assertNull(responseSuccess)

            /* Failure */
            assertNotNull(responseFailure)
            assertEquals(responseFailure, fakeHttpError)
            assertEquals(responseFailure?.errorCode, fakeHttpError.errorCode)
            assertEquals(responseFailure?.throwable, fakeHttpError.throwable)
            assertEquals(responseFailure?.throwable?.message, fakeHttpError.throwable.message)

            /* No Network */
            assertNull(responseNoNetwork)
        }
    }

    @Test
    fun `test allAlbumUseCase no network`() {
        runBlocking {

            whenever(albumRepository.loadingAllAlbum()).thenReturn(NoNetwork)

            var responseSuccess: AllAlbumResponse? = null
            var responseFailure: HttpError? = null
            var responseNoNetwork: NoNetwork? = null

            allAlbumUseCase()
                .onSuccess { responseSuccess = it }
                .onFailure { responseFailure = it }
                .noNetwork { responseNoNetwork = NoNetwork }

            assertNull(responseSuccess)

            /* Failure */
            assertNull(responseFailure)

            /* No Network */
            assertNotNull(responseNoNetwork)
        }
    }
}
