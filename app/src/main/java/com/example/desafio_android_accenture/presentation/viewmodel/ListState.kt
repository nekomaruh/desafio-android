package com.example.desafio_android_accenture.presentation.viewmodel

/*
sealed class ListState() {
    object Loading : ListState()
    object Error: ListState()
    //object NoData: ListState()
    data class Success(val list: List<Any>) : ListState()
}

 */




sealed class ListState<T> {
    class Loading<T> : ListState<T>()
    class Error<T> : ListState<T>()
    //class NoData<T> : ListState<T>()
    data class Success<T>(val list: List<T>) : ListState<T>()
}

