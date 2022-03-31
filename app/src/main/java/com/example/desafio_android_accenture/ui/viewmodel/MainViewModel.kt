package com.example.desafio_android_accenture.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.domain.GetRepositoriesUseCase
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    // Live Data
    private val repositoryList = MutableLiveData<List<RepositoryModel>>()
    val isLoading = MutableLiveData<Boolean>()
    var pageIndex = 1

    // Use Cases
    private var getRepositoriesUseCase = GetRepositoriesUseCase()

    fun onCreate(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getRepositoriesUseCase(pageIndex)

            if(!result.isNullOrEmpty()){
                isLoading.postValue(false)
                repositoryList.postValue(result)
            }
        }
    }

    fun getRepository(): MutableLiveData<List<RepositoryModel>> = repositoryList
}