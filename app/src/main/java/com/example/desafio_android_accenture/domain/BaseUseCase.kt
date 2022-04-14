package com.example.desafio_android_accenture.domain

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<P, T, Q, L : LiveData<*>>(
    protected open val backgroundContext: CoroutineContext = Dispatchers.IO,
    protected open val foregroundContext: CoroutineContext = Dispatchers.Main
)  {

    abstract suspend fun executeOnBackground(params: P): T?

    open suspend fun executeOnException(throwable: Throwable): Q? = null
    open suspend fun executeOnHook(liveData: L, response: T) {}

    protected abstract suspend fun onStart(liveData: L)
    protected abstract suspend fun onEmpty(liveData: L)
    protected abstract suspend fun onSuccess(liveData: L, response: T)
    protected abstract suspend fun onFailure(liveData: L, error: Q?)
    protected abstract suspend fun onCancel(liveData: L)

    fun execute(params: P, liveData: L, coroutineScope: CoroutineScope) {
        coroutineScope.launch(foregroundContext) {
            onStart(liveData)

            runCatching {
                withContext(backgroundContext) {
                    executeOnBackground(params)?.also { executeOnHook(liveData, it) }
                }
            }.onSuccess { response ->
                if (response != null)
                    onSuccess(liveData, response)
                else
                    onEmpty(liveData)
            }.onFailure { throwable ->
                when (throwable) {
                    is CancellationException -> onCancel(liveData)
                    else -> {
                        val error = runCatching { executeOnException(throwable) }.getOrNull()

                        onFailure(liveData, error)
                    }
                }
            }
        }
    }
}