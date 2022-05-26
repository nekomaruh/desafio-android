package com.example.desafio_android_accenture.presentation.viewmodel

import android.util.Log
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
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
) : ViewModel() {

    val repositoryListState = MutableLiveData<ListState<RepositoryModel>>()
    private val indexState = MutableLiveData(0)

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

    fun getNextRepositoriesPage() {
        indexState.value?.let {
            indexState.value = it + 1
            getRepositories(it)
        }
    }

}



