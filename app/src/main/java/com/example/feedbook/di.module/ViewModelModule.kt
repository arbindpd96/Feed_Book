package com.example.feedbook.di.module

import com.example.feedbook.ui.viewmodel.vm.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get(),get())
    }
}