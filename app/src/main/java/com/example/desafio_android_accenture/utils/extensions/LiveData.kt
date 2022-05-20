package com.example.desafio_android_accenture.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.domain.BaseUseCase
import com.example.desafio_android_accenture.presentation.viewmodel.ListState
import kotlin.reflect.KFunction1

fun <P, T, Q, L, U : BaseUseCase<P, T, Q, L>> ViewModel.execute(
    useCase: U,
    params: P,
    liveData: L
) = useCase.execute(params, liveData, viewModelScope)


fun <T, L : MutableLiveData<T>> LifecycleOwner.observe(liveData: L, body: (T) -> Unit) =
    liveData.observe(this, Observer(body))
    /*
    liveData.observe(this) {
        body(it)
    }
     */
