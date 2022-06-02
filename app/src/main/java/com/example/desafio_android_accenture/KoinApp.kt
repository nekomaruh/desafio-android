package com.example.desafio_android_accenture

import android.app.Application
import com.example.desafio_android_accenture.core.di.modules.imageModule
import com.example.desafio_android_accenture.core.di.modules.networkModule
import com.example.desafio_android_accenture.core.di.modules.useCaseModule
import com.example.desafio_android_accenture.core.di.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KoinApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@KoinApp)
            modules(
                networkModule,
                imageModule,
                viewModelModule,
                useCaseModule,
            )
        }
    }
}