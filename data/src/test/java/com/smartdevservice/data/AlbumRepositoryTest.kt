package com.smartdevservice.data

import android.content.Context
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.smartdevservice.data.Utils.fakeResponseBodyFailure
import com.smartdevservice.data.common.Connectivity
import com.smartdevservice.data.di.commonModule
import com.smartdevservice.data.di.networkingModule
import com.smartdevservice.data.di.repositoryModule
import com.smartdevservice.data.network.DeezerApi
import com.smartdevservice.data.repository.AlbumRepositoryImpl
import com.smartdevservice.domain.*
import com.smartdevservice.domain.UtilsTest.FAKE_FAILURE_ERROR_CODE
import com.smartdevservice.domain.UtilsTest.FAKE_FAILURE_ERROR_MSG
import com.smartdevservice.domain.UtilsTest.successAllAlbumResponse
import com.smartdevservice.domain.UtilsTest.successDetailsAlbumResponse
import com.smartdevservice.domain.UtilsTest.urlIdAlbum
import com.smartdevservice.domain.model.AllAlbumResponse
import com.smartdevservice.domain.model.DetailsAlbumResponse
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.mockito.Mockito
import retrofit2.Response

class AlbumRepositoryTest {

    private val context = Mockito.mock(Context::class.java)
    private val deezerApi: DeezerApi = mock()
    private val connectivity: Connectivity = mock()
    private val albumRepository = AlbumRepositoryImpl(deezerApi, connectivity)


    @Before
    fun before() {
        startKoin {
            androidContext(context)
            modules(commonModule + networkingModule + repositoryModule)
        }
    }

    @After
    fun after() {
        stopKoin()
    }


    @Test
    fun `test loadingAllAlbums calls api on success`() {
        runBlocking {
            whenever(connectivity.hasNetworkAccess()).thenReturn(true)
            whenever(deezerApi.loadingAllAlbums()).thenReturn(
                Response.success(
                    successAllAlbumResponse
                )
            )

            var responseSuccess: AllAlbumResponse? = null
            var responseFailure: HttpError? = null
            var responseNoNetwork: NoNetwork? = null

            albumRepository.loadingAllAlbum()
                .onSuccess { responseSuccess = it }
                .onFailure { responseFailure = it }
                .noNetwork { responseNoNetwork = NoNetwork }

            assertNotNull(responseSuccess)
            assertEquals(responseSuccess, successAllAlbumResponse)
            assertEquals(responseSuccess?.data, successAllAlbumResponse.data)
            assertEquals(responseSuccess?.data?.size, successAllAlbumResponse.data?.size)
            assertEquals(responseSuccess?.data?.get(0), successAllAlbumResponse.data?.get(0))
            assertEquals(
                responseSuccess?.data?.get(0)?.artist,
                successAllAlbumResponse.data?.get(0)?.artist
            )
            /* Failure */
            assertNull(responseFailure?.errorCode)
            assertNull(responseFailure?.throwable?.message)

            /* no Network */
            assertNull(responseNoNetwork)
        }
    }

    @Test
    fun `test loadingAllAlbums calls api on failure`() {
        runBlocking {
            whenever(connectivity.hasNetworkAccess()).thenReturn(true)
            whenever(deezerApi.loadingAllAlbums()).thenReturn(
                Response.error(
                    FAKE_FAILURE_ERROR_CODE,
                    fakeResponseBodyFailure
                )
            )

            var responseSuccess: AllAlbumResponse? = null
            var responseFailure: HttpError? = null
            var responseNoNetwork: NoNetwork? = null

            albumRepository.loadingAllAlbum()
                .onSuccess { responseSuccess = it }
                .onFailure { responseFailure = it }
                .noNetwork { responseNoNetwork = NoNetwork }

            assertNull(responseSuccess)

            /* Failure */
            assertEquals(responseFailure?.errorCode, FAKE_FAILURE_ERROR_CODE)
            assertEquals(responseFailure?.throwable?.message, FAKE_FAILURE_ERROR_MSG)

            /* no Network */
            assertNull(responseNoNetwork)
        }
    }

    @Test
    fun `test loadingAllAlbums calls api no network`() {
        runBlocking {
            whenever(connectivity.hasNetworkAccess()).thenReturn(false)
            whenever(deezerApi.loadingAllAlbums()).thenReturn(
                Response.success(
                    successAllAlbumResponse
                )
            )

            var responseSuccess: AllAlbumResponse? = null
            var responseFailure: HttpError? = null
            var responseNoNetwork: NoNetwork? = null

            albumRepository.loadingAllAlbum()
                .onSuccess { responseSuccess = it }
                .onFailure { responseFailure = it }
                .noNetwork { responseNoNetwork = NoNetwork }

            assertNull(responseSuccess)

            /* Failure */
            assertNull(responseFailure)

            /* no Network */
            assertNotNull(responseNoNetwork)
        }
    }

    @Test
    fun `test loadingDetailsAlbums calls api on success`() {
        runBlocking {
            whenever(connectivity.hasNetworkAccess()).thenReturn(true)
            whenever(deezerApi.loadingDetailsAlbum(urlIdAlbum)).thenReturn(
                Response.success(
                    successDetailsAlbumResponse
                )
            )

            var responseSuccess: DetailsAlbumResponse? = null
            var responseFailure: HttpError? = null
            var responseNoNetwork: NoNetwork? = null

            albumRepository.loadingDetailsAlbum(urlIdAlbum)
                .onSuccess { responseSuccess = it }
                .onFailure { responseFailure = it }
                .noNetwork { responseNoNetwork = NoNetwork }

            assertNotNull(responseSuccess)
            assertEquals(responseSuccess, successDetailsAlbumResponse)
            assertEquals(responseSuccess?.data, successDetailsAlbumResponse.data)
            assertEquals(responseSuccess?.data?.size, successDetailsAlbumResponse.data?.size)
            assertEquals(responseSuccess?.data?.get(0), successDetailsAlbumResponse.data?.get(0))
            assertEquals(
                responseSuccess?.data?.get(0)?.artist,
                successDetailsAlbumResponse.data?.get(0)?.artist
            )
            /* Failure */
            assertNull(responseFailure?.errorCode)
            assertNull(responseFailure?.throwable?.message)

            /* no Network */
            assertNull(responseNoNetwork)
        }
    }

    @Test
    fun `test loadingDetailsAlbums calls api on failure`() {
        runBlocking {
            whenever(connectivity.hasNetworkAccess()).thenReturn(true)
            whenever(deezerApi.loadingDetailsAlbum(urlIdAlbum)).thenReturn(
                Response.error(
                    FAKE_FAILURE_ERROR_CODE,
                    fakeResponseBodyFailure
                )
            )

            var responseSuccess: DetailsAlbumResponse? = null
            var responseFailure: HttpError? = null
            var responseNoNetwork: NoNetwork? = null

            albumRepository.loadingDetailsAlbum(urlIdAlbum)
                .onSuccess { responseSuccess = it }
                .onFailure { responseFailure = it }
                .noNetwork { responseNoNetwork = NoNetwork }

            assertNull(responseSuccess)

            /* Failure */
            assertEquals(responseFailure?.errorCode, FAKE_FAILURE_ERROR_CODE)
            assertEquals(responseFailure?.throwable?.message, FAKE_FAILURE_ERROR_MSG)

            /* no Network */
            assertNull(responseNoNetwork)
        }
    }

    @Test
    fun `test loadingDetailsAlbums calls api no network`() {
        runBlocking {
            whenever(connectivity.hasNetworkAccess()).thenReturn(false)
            whenever(deezerApi.loadingDetailsAlbum(urlIdAlbum)).thenReturn(
                Response.success(
                    successDetailsAlbumResponse
                )
            )

            var responseSuccess: DetailsAlbumResponse? = null
            var responseFailure: HttpError? = null
            var responseNoNetwork: NoNetwork? = null

            albumRepository.loadingDetailsAlbum(urlIdAlbum)
                .onSuccess { responseSuccess = it }
                .onFailure { responseFailure = it }
                .noNetwork { responseNoNetwork = NoNetwork }

            assertNull(responseSuccess)

            /* Failure */
            assertNull(responseFailure)

            /* no Network */
            assertNotNull(responseNoNetwork)
        }
    }
}