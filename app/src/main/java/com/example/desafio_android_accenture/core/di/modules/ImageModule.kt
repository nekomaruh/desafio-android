package com.example.desafio_android_accenture.core.di.modules

import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderImpl

import org.koin.dsl.module

val imageModule = module {
    single<ImageLoader> {
        ImageLoaderImpl()
    }
}

