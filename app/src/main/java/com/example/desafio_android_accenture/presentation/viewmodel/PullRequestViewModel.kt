package com.example.desafio_android_accenture.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.desafio_android_accenture.domain.GetPullRequestsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PullRequestViewModel (
    private val getPullRequestsUseCase: GetPullRequestsUseCase
) : ViewModel() {

    private val _pullRequestListState = MutableStateFlow<UiState>(UiState.Empty)

    val pullRequestListState: StateFlow<UiState> = _pullRequestListState

    fun getPullRequests(user: String, repository: String) = viewModelScope.launch {
        Log.d("GG","Cargando pull requests")
        _pullRequestListState.value = UiState.Loading
        kotlin.runCatching {
            Log.d("GG","Run catching")
            getPullRequestsUseCase(user, repository)
        }.onSuccess {
            Log.d("GG","Success")
            _pullRequestListState.value = UiState.Success(it)
        }.onFailure {
            _pullRequestListState.value = UiState.Error("Error")
        }
    }
}