package com.example.desafio_android_accenture.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.domain.GetPullRequestsUseCase
import com.example.desafio_android_accenture.domain.GetRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val getPullRequestsUseCase: GetPullRequestsUseCase
): ViewModel() {
    // Live Data
    val repositoryList = MutableLiveData<List<RepositoryModel>>()
    val isLoading = MutableLiveData<Boolean>()
    var pageIndex = 1

    init {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getRepositoriesUseCase(pageIndex)

            if(!result.isNullOrEmpty()){
                repositoryList.postValue(result)
            }
            isLoading.postValue(false)
        }
    }

    fun getPullRequests(user:String, repository:String): MutableLiveData<List<PullRequestModel>> {
        val pullRequestList = MutableLiveData<List<PullRequestModel>>()
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getPullRequestsUseCase(user,repository)
            pullRequestList.postValue(result)
            isLoading.postValue(false)
        }
        return pullRequestList
    }

}