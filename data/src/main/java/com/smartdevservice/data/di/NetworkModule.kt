package com.smartdevservice.data.di

import android.util.Log
import com.google.gson.GsonBuilder
import com.smartdevservice.data.BuildConfig
import com.smartdevservice.data.network.DeezerApi

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.deezer.com/2.0/"
const val EXTEND_URL = "album/"

val networkingModule = module {

    single { GsonBuilder().setLenient().create() }
    single { GsonConverterFactory.create(get()) }

    single {
        HttpLoggingInterceptor {
                message -> Log.d("OkHttp", message)
        }.apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    single {
        OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(get() as HttpLoggingInterceptor)
            }
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(get() as GsonConverterFactory)
            .build()
    }

    single { get<Retrofit>().create(DeezerApi::class.java) }
}