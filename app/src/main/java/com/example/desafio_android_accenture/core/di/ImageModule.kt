package com.example.desafio_android_accenture.core.di

import com.example.desafio_android_accenture.data.imageloader.ImageLoader
import com.example.desafio_android_accenture.data.imageloader.ImageLoaderService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ImageModule {

    @Singleton
    @Provides
    fun provideImageLoader(): ImageLoader {
        return ImageLoaderService()
    }
}