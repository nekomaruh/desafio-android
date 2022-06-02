package com.example.desafio_android_accenture.utils.extensions

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

fun < T, L : StateFlow<T>> LifecycleOwner.observeFlow(
    flowState: L,
    body: (T) -> Unit,
) {
    this.lifecycleScope.launchWhenStarted {
        flowState.collect { body(it) }
    }
}