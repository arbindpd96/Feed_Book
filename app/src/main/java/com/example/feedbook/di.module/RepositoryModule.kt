package com.example.feedbook.di.module
import com.example.feedbook.data.api.api.ApiHelper
import com.example.feedbook.data.api.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}