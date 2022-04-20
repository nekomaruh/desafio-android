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

sealed class MyListState {
    object Loading : MyListState()
    object Error: MyListState()
    data class Success(val list: List<Any>) : MyListState()
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val getPullRequestsUseCase: GetPullRequestsUseCase,
) : ViewModel() {
    // Live Data
    val repositoryList = MutableLiveData<List<RepositoryModel>>()
    val repositoryListState = MutableLiveData<MyListState>()
    val isLoading = MutableLiveData<Boolean>()

    val TAG = "TAG"

    init {

        Log.i(TAG,"ON INIT VIEWMODEL")
        //getRepositories(1)
    }

    fun getRepositories(index: Int) {
        Log.i(TAG,"LOAD REPOSITORIES FROM VIEWMODEL")
        viewModelScope.launch {
            repositoryListState.postValue(MyListState.Loading)
            val result = getRepositoriesUseCase(index)
            if (!result.isNullOrEmpty()) {
                repositoryList.postValue(result)
                repositoryListState.postValue(MyListState.Success(result))
            }else{
                repositoryList.postValue(emptyList())
                repositoryListState.postValue(MyListState.Error)
            }
        }
    }

    fun getPullRequests(user: String, repository: String): MutableLiveData<List<PullRequestModel>> {
        val pullRequestList = MutableLiveData<List<PullRequestModel>>()
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getPullRequestsUseCase(user, repository)
            pullRequestList.postValue(result)
            isLoading.postValue(false)
        }
        return pullRequestList
    }

}



