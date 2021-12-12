package com.smartdevservice.deezerapp

import androidx.multidex.MultiDexApplication
import com.smartdevservice.data.di.commonModule
import com.smartdevservice.data.di.networkingModule
import com.smartdevservice.data.di.repositoryModule
import com.smartdevservice.deezerapp.di.presentationModule
import com.smartdevservice.domain.di.interactionModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

private val appModules = listOf(presentationModule)
private val domainModules = listOf(interactionModule)
private val dataModules = listOf(commonModule, networkingModule, repositoryModule)
private val allModules = appModules + domainModules + dataModules

class DeezerApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DeezerApp )
            if (BuildConfig.DEBUG) {
                androidLogger(Level.ERROR)
            }
            modules(allModules)
        }

        Timber.plant(Timber.DebugTree())
    }

}