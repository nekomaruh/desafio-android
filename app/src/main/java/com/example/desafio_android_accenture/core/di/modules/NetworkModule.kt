package com.example.desafio_android_accenture.core.di.modules

import com.example.desafio_android_accenture.data.network.GithubApiClient
import com.example.desafio_android_accenture.data.network.GithubRepository
import com.example.desafio_android_accenture.data.network.GithubRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GithubApiClient::class.java)
    }

    /** Factory: Crea una nueva instancia de la implementación
     * del repositorio cada vez que es requerida */
    single<GithubRepository> {
        GithubRepositoryImpl(get())
    }
}