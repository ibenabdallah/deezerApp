package com.smartdevservice.domain.di

import com.smartdevservice.domain.usecase.AllAlbumUseCase
import com.smartdevservice.domain.usecase.AllAlbumUseCaseImpl
import org.koin.dsl.module

val interactionModule = module {

    factory<AllAlbumUseCase> { AllAlbumUseCaseImpl(get()) }

}


