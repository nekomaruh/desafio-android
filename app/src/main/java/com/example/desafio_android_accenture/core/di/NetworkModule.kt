package com.example.desafio_android_accenture.core.di

import com.example.desafio_android_accenture.data.network.GithubApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// InstallIn scope activity: Will create an instance for every activity
// InstallIn scope viewModel: Instance for every viewmodel
// InstallIn scope application: Application level across app

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton // Only one instance for Retrofit
    @Provides // Provider
    fun provideRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideGithubApiClient(retrofit: Retrofit):GithubApiClient{
        return retrofit.create(GithubApiClient::class.java)
    }

}