package com.example.desafio_android_accenture.presentation.viewmodel

sealed class UiState {
    object Loading : UiState()
    object Empty : UiState()
    data class Success(val list: List<Any>) : UiState()
    data class Error(val message: String) : UiState()
}