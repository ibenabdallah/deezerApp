package com.smartdevservice.data.di

import com.smartdevservice.data.repository.AlbumRepositoryImpl
import com.smartdevservice.domain.repository.AlbumRepository
import org.koin.dsl.module

val repositoryModule = module {

    factory<AlbumRepository> { AlbumRepositoryImpl(get(), get()) }

}