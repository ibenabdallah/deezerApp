package com.smartdevservice.deezerapp

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.smartdevservice.data.common.Connectivity
import com.smartdevservice.data.common.ConnectivityImpl
import com.smartdevservice.data.di.commonModule
import com.smartdevservice.data.di.networkingModule
import com.smartdevservice.data.di.repositoryModule
import com.smartdevservice.data.repository.NETWORK_ERROR_MSG
import com.smartdevservice.deezerapp.di.presentationModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import timber.log.Timber

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
open class BaseViewModelTest : KoinTest {

    private val context = Mockito.mock(Context::class.java)
    protected val connectivity: Connectivity = mock()
    protected val throwable = Throwable(NETWORK_ERROR_MSG)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    open fun before() {
        startKoin {
            androidContext(context)
            modules( presentationModule + commonModule + networkingModule + repositoryModule)
        }
    }
    @Test
    public fun test0(){
        Timber.d("test ...")
    }

    @After
    open fun after() {
        stopKoin()
    }
}