package com.example.desafio_android_accenture.utils.extensions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android_accenture.domain.BaseUseCase

fun <P, T, Q, L, U : BaseUseCase<P, T, Q, L>> ViewModel.execute(
    useCase: U,
    params: P,
    liveData: L
) = useCase.execute(params, liveData, viewModelScope)