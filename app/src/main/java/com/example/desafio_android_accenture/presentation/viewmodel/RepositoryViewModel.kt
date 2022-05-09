package com.example.desafio_android_accenture.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.domain.GetRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class RepositoryViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    val repositoryListState = MutableLiveData<ListState<RepositoryModel>>()

    fun getRepositories(index: Int) {
        viewModelScope.launch {
            repositoryListState.postValue(ListState.Loading())
            val result = getRepositoriesUseCase(index)
            if (result.isEmpty() || !result.isNullOrEmpty()) {
                repositoryListState.postValue(ListState.Success(result))
            } else {
                repositoryListState.postValue(ListState.Error())
            }
        }
    }

}



