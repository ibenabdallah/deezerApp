package com.smartdevservice.deezerapp.di

import com.smartdevservice.deezerapp.ui.list.AlbumViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel { AlbumViewModel(get()) }

}