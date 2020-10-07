package com.example.feedbook

import android.app.Application
import com.example.feedbook.di.module.appModule
import com.example.feedbook.di.module.repoModule
import com.example.feedbook.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class FeedBook : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FeedBook)
            modules(appModule, repoModule, viewModelModule)
        }
    }
}
