package com.smartdevservice.deezerapp

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.smartdevservice.deezerapp.base.*
import com.smartdevservice.deezerapp.ui.list.AlbumViewModel
import com.smartdevservice.domain.Failure
import com.smartdevservice.domain.HttpError
import com.smartdevservice.domain.Success
import com.smartdevservice.domain.UtilsTest.successAllAlbumResponse
import com.smartdevservice.domain.model.Album
import com.smartdevservice.domain.usecase.AllAlbumUseCase
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

    private val albumViewModel by lazy {
        AlbumViewModel(connectivity, allAlbumUseCase)
    }

    @Mock
    private lateinit var allAlbumObserver: Observer<ViewState<ArrayList<Album>>>

    @Before
    override fun before() {
        super.before()
        albumViewModel.viewState.observeForever(allAlbumObserver)
    }

    @After
    override fun after() {
        albumViewModel.viewState.removeObserver(allAlbumObserver)
        super.after()
    }

    @Test
    fun `test loading all album sets liveData value when success`() {

        testCoroutineRule.runBlockingTest {

            whenever(connectivity.hasNetworkAccess()).thenReturn(true)

            doReturn(Success(successAllAlbumResponse))
                .`when`(allAlbumUseCase)
                .invoke()

            albumViewModel.loadingAllAlbum()

            verify(allAlbumObserver).onChanged(LoadingState())
            verify(allAlbumObserver).onChanged(SuccessState(successAllAlbumResponse.data))
            verify(allAlbumObserver, never()).onChanged(NoInternetState())
            verify(allAlbumObserver, never()).onChanged(FailureState(throwable))
        }
    }

    @Test
    fun `test loading all album sets liveData value when no internet`() {

        testCoroutineRule.runBlockingTest {

            whenever(connectivity.hasNetworkAccess()).thenReturn(false)

            doReturn(Success(successAllAlbumResponse))
                .`when`(allAlbumUseCase)
                .invoke()

            albumViewModel.loadingAllAlbum()

            verify(allAlbumObserver, never()).onChanged(LoadingState())
            verify(allAlbumObserver, never()).onChanged(SuccessState(null))
            verify(allAlbumObserver, never()).onChanged(FailureState(throwable))
            verify(allAlbumObserver).onChanged(NoInternetState())
        }
    }

    @Test
    fun `test loading all album sets liveData value when failure`() {
        testCoroutineRule.runBlockingTest {

            whenever(connectivity.hasNetworkAccess()).thenReturn(true)

            doReturn(Failure(HttpError(throwable)))
                .`when`(allAlbumUseCase)
                .invoke()

            albumViewModel.loadingAllAlbum()

            verify(allAlbumObserver).onChanged(LoadingState())
            verify(allAlbumObserver, never()).onChanged(SuccessState(successAllAlbumResponse.data))
            verify(allAlbumObserver, never()).onChanged(NoInternetState())
            verify(allAlbumObserver).onChanged(FailureState(throwable))
        }
    }
}