package com.smartdevservice.deezerapp

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.smartdevservice.deezerapp.base.*
import com.smartdevservice.deezerapp.ui.AlbumViewModel
import com.smartdevservice.domain.Failure
import com.smartdevservice.domain.NoNetwork
import com.smartdevservice.domain.Success
import com.smartdevservice.domain.UtilsTest.fakeHttpError
import com.smartdevservice.domain.UtilsTest.successAllAlbumResponse
import com.smartdevservice.domain.UtilsTest.successDetailsAlbumResponse
import com.smartdevservice.domain.UtilsTest.urlIdAlbum
import com.smartdevservice.domain.model.Album
import com.smartdevservice.domain.model.Track
import com.smartdevservice.domain.usecase.AllAlbumUseCase
import com.smartdevservice.domain.usecase.DetailsAlbumUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class AlbumViewModelTest : BaseViewModelTest() {

    private val allAlbumUseCase: AllAlbumUseCase = mock()
    private val detailsAlbumUseCase: DetailsAlbumUseCase = mock()

    private val albumViewModel by lazy {
        AlbumViewModel(allAlbumUseCase, detailsAlbumUseCase)
    }

    @Mock
    private lateinit var allAlbumObserver: Observer<ViewState<ArrayList<Album>>>
    @Mock
    private lateinit var detailsAlbumObserver: Observer<ViewState<ArrayList<Track>>>

    @Before
    override fun before() {
        super.before()
        albumViewModel.albumsState.observeForever(allAlbumObserver)
        albumViewModel.detailsState.observeForever(detailsAlbumObserver)
    }

    @After
    override fun after() {
        albumViewModel.albumsState.removeObserver(allAlbumObserver)
        albumViewModel.detailsState.removeObserver(detailsAlbumObserver)
        super.after()
    }

    @Test
    fun `test loading all album sets liveData value when success`() {

        testCoroutineRule.runBlockingTest {

            doReturn(Success(successAllAlbumResponse))
                .`when`(allAlbumUseCase)
                .invoke()

            albumViewModel.loadingAllAlbum()

            verify(allAlbumObserver).onChanged(LoadingState)
            verify(allAlbumObserver).onChanged(SuccessState(successAllAlbumResponse.data))
            verify(allAlbumObserver, never()).onChanged(NoNetworkState)
            verify(allAlbumObserver, never()).onChanged(FailureState(fakeHttpError))
        }
    }

    @Test
    fun `test loading all album sets liveData value when no network`() {

        testCoroutineRule.runBlockingTest {

            doReturn(NoNetwork)
                .`when`(allAlbumUseCase)
                .invoke()

            albumViewModel.loadingAllAlbum()

            verify(allAlbumObserver).onChanged(LoadingState)
            verify(allAlbumObserver, never()).onChanged(SuccessState(null))
            verify(allAlbumObserver, never()).onChanged(FailureState(fakeHttpError))
            verify(allAlbumObserver).onChanged(NoNetworkState)
        }
    }

    @Test
    fun `test loading all album sets liveData value when failure`() {
        testCoroutineRule.runBlockingTest {

            doReturn(Failure(fakeHttpError))
                .`when`(allAlbumUseCase)
                .invoke()

            albumViewModel.loadingAllAlbum()

            verify(allAlbumObserver).onChanged(LoadingState)
            verify(allAlbumObserver, never()).onChanged(SuccessState(successAllAlbumResponse.data))
            verify(allAlbumObserver, never()).onChanged(NoNetworkState)
            verify(allAlbumObserver).onChanged(FailureState(fakeHttpError))
        }
    }

    @Test
    fun `test loading details album sets liveData value when success`() {

        testCoroutineRule.runBlockingTest {

            doReturn(Success(successDetailsAlbumResponse))
                .`when`(detailsAlbumUseCase)
                .invoke(urlIdAlbum)

            albumViewModel.loadingDetailsAlbum(urlIdAlbum)

            verify(detailsAlbumObserver).onChanged(LoadingState)
            verify(detailsAlbumObserver).onChanged(SuccessState(successDetailsAlbumResponse.data))
            verify(detailsAlbumObserver, never()).onChanged(NoNetworkState)
            verify(detailsAlbumObserver, never()).onChanged(FailureState(fakeHttpError))
        }
    }

    @Test
    fun `test loading details album sets liveData value when no network`() {

        testCoroutineRule.runBlockingTest {

            doReturn(NoNetwork)
                .`when`(detailsAlbumUseCase)
                .invoke(urlIdAlbum)

            albumViewModel.loadingDetailsAlbum(urlIdAlbum)

            verify(detailsAlbumObserver).onChanged(LoadingState)
            verify(detailsAlbumObserver, never()).onChanged(SuccessState(null))
            verify(detailsAlbumObserver, never()).onChanged(FailureState(fakeHttpError))
            verify(detailsAlbumObserver).onChanged(NoNetworkState)
        }
    }

    @Test
    fun `test loading details album sets liveData value when failure`() {
        testCoroutineRule.runBlockingTest {

            doReturn(Failure(fakeHttpError))
                .`when`(detailsAlbumUseCase)
                .invoke(urlIdAlbum)

            albumViewModel.loadingDetailsAlbum(urlIdAlbum)

            verify(detailsAlbumObserver).onChanged(LoadingState)
            verify(detailsAlbumObserver, never()).onChanged(SuccessState(successDetailsAlbumResponse.data))
            verify(detailsAlbumObserver, never()).onChanged(NoNetworkState)
            verify(detailsAlbumObserver).onChanged(FailureState(fakeHttpError))
        }
    }
}