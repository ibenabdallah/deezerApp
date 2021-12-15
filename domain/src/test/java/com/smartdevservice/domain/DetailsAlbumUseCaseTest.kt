package com.smartdevservice.domain

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.smartdevservice.domain.UtilsTest.errorDetailsAlbumResponse
import com.smartdevservice.domain.UtilsTest.fakeHttpError
import com.smartdevservice.domain.UtilsTest.successDetailsAlbumResponse
import com.smartdevservice.domain.UtilsTest.urlIdAlbum
import com.smartdevservice.domain.model.DetailsAlbumResponse
import com.smartdevservice.domain.repository.AlbumRepository
import com.smartdevservice.domain.usecase.DetailsAlbumUseCaseImpl
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class DetailsAlbumUseCaseTest {

    private val albumRepository: AlbumRepository = mock()

    private val detailsAlbumUseCase by lazy { DetailsAlbumUseCaseImpl(albumRepository) }

    @Test
    fun `test detailsAlbumUseCase calls AlbumRepository`() {
        runBlocking {
            detailsAlbumUseCase(urlIdAlbum)
            verify(albumRepository).loadingDetailsAlbum(urlIdAlbum)
        }
    }

    @Test
    fun `test detailsAlbumUseCase success`() {
        runBlocking {

            whenever(albumRepository.loadingDetailsAlbum(urlIdAlbum)).thenReturn(Success(successDetailsAlbumResponse))

            var responseSuccess: DetailsAlbumResponse? = null
            var responseFailure: HttpError? = null
            var responseNoNetwork: NoNetwork? = null

            detailsAlbumUseCase(urlIdAlbum)
                .onSuccess { responseSuccess = it }
                .onFailure { responseFailure = it }
                .noNetwork { responseNoNetwork = NoNetwork }

            assertNotNull(responseSuccess)
            assertEquals(responseSuccess, successDetailsAlbumResponse)
            assertEquals(responseSuccess?.data, successDetailsAlbumResponse.data)
            assertEquals(responseSuccess?.data?.size, successDetailsAlbumResponse.data?.size)
            assertEquals(responseSuccess?.data?.get(0), successDetailsAlbumResponse.data?.get(0))
            assertEquals(responseSuccess?.data?.get(0)?.artist, successDetailsAlbumResponse.data?.get(0)?.artist)

            /* Failure */
            assertNull(responseFailure?.errorCode)
            assertNull(responseFailure?.throwable)

            /* No Network */
            assertNull(responseNoNetwork)
        }
    }

    @Test
    fun `test detailsAlbumUseCase error no data`() {
        runBlocking {

            whenever(albumRepository.loadingDetailsAlbum(urlIdAlbum)).thenReturn(Success(errorDetailsAlbumResponse))

            var responseSuccess: DetailsAlbumResponse? = null
            var responseFailure: HttpError? = null
            var responseNoNetwork: NoNetwork? = null

            detailsAlbumUseCase(urlIdAlbum)
                .onSuccess { responseSuccess = it }
                .onFailure { responseFailure = it }
                .noNetwork { responseNoNetwork = NoNetwork }

            assertNotNull(responseSuccess)
            assertEquals(responseSuccess, errorDetailsAlbumResponse)
            assertNull(responseSuccess?.data)
            assertEquals(responseSuccess?.error, errorDetailsAlbumResponse.error)
            assertEquals(responseSuccess?.error?.code, errorDetailsAlbumResponse.error?.code)
            assertEquals(responseSuccess?.error?.message, errorDetailsAlbumResponse.error?.message)
            assertEquals(responseSuccess?.error?.type, errorDetailsAlbumResponse.error?.type)

            /* Failure */
            assertNull(responseFailure?.errorCode)
            assertNull(responseFailure?.throwable)

            /* No Network */
            assertNull(responseNoNetwork)
        }
    }

    @Test
    fun `test detailsAlbumUseCase failure`() {
        runBlocking {

            whenever(albumRepository.loadingDetailsAlbum(urlIdAlbum)).thenReturn(Failure(fakeHttpError))

            var responseSuccess: DetailsAlbumResponse? = null
            var responseFailure: HttpError? = null
            var responseNoNetwork: NoNetwork? = null

            detailsAlbumUseCase(urlIdAlbum)
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
    fun `test detailsAlbumUseCase no network`() {
        runBlocking {

            whenever(albumRepository.loadingDetailsAlbum(urlIdAlbum)).thenReturn(NoNetwork)

            var responseSuccess: DetailsAlbumResponse? = null
            var responseFailure: HttpError? = null
            var responseNoNetwork: NoNetwork? = null
            
            detailsAlbumUseCase(urlIdAlbum)
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
