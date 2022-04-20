package com.example.desafio_android_accenture.presentation.viewmodel

sealed class ListState {
    object Loading : ListState()
    object Error: ListState()
    data class Success(val list: List<Any>) : ListState()
}