package com.smartdevservice.domain.di

import com.smartdevservice.domain.usecase.AllAlbumUseCase
import com.smartdevservice.domain.usecase.AllAlbumUseCaseImpl
import com.smartdevservice.domain.usecase.DetailsAlbumUseCase
import com.smartdevservice.domain.usecase.DetailsAlbumUseCaseImpl
import org.koin.dsl.module

val interactionModule = module {

    factory<AllAlbumUseCase> { AllAlbumUseCaseImpl(get()) }

    factory<DetailsAlbumUseCase> { DetailsAlbumUseCaseImpl(get()) }

}


