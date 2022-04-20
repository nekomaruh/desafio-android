package com.example.desafio_android_accenture.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.domain.GetPullRequestsUseCase
import com.example.desafio_android_accenture.domain.GetRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


val TAG = "TAG"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val getPullRequestsUseCase: GetPullRequestsUseCase,
) : ViewModel() {

    // Live Data
    val repositoryList = MutableLiveData<List<RepositoryModel>>()
    val pullRequestList = MutableLiveData<List<PullRequestModel>>()

    // States
    val repositoryListState = MutableLiveData<ListState>()
    val pullRequestListState = MutableLiveData<ListState>()

    init {
        Log.i(TAG,"ON INIT VIEWMODEL")
        //getRepositories(1)
    }

    fun getRepositories(index: Int) {
        Log.i(TAG,"LOAD REPOSITORIES FROM VIEWMODEL")
        viewModelScope.launch {
            repositoryListState.postValue(ListState.Loading)
            val result = getRepositoriesUseCase(index)
            if (!result.isNullOrEmpty()) {
                repositoryList.postValue(result)
                repositoryListState.postValue(ListState.Success(result))
            }else{
                repositoryList.postValue(emptyList())
                repositoryListState.postValue(ListState.Error)
            }
        }
    }

    fun getPullRequests(user: String, repository: String) {
        Log.i(TAG,"LOAD PULL REQUESTS FROM VIEWMODEL")
        viewModelScope.launch {
            pullRequestListState.postValue(ListState.Loading)
            val result = getPullRequestsUseCase(user, repository)
            if (!result.isNullOrEmpty()) {
                pullRequestList.postValue(result)
                pullRequestListState.postValue(ListState.Success(result))
            }else{
                pullRequestList.postValue(emptyList())
                pullRequestListState.postValue(ListState.Error)
            }
        }
    }

}



