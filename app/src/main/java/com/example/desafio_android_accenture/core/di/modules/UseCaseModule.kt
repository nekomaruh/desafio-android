package com.example.desafio_android_accenture.core.di.modules

import com.example.desafio_android_accenture.domain.GetPullRequestsUseCase
import com.example.desafio_android_accenture.domain.GetRepositoriesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single {
        GetRepositoriesUseCase(get())
    }
    single {
        GetPullRequestsUseCase(get())
    }
}