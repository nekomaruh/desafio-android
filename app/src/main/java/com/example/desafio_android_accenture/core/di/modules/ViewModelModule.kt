package com.example.desafio_android_accenture.core.di.modules

import com.example.desafio_android_accenture.presentation.viewmodel.PullRequestViewModel
import com.example.desafio_android_accenture.presentation.viewmodel.RepositoryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        RepositoryViewModel(get())
    }
    viewModel {
        PullRequestViewModel(get())
    }
}
