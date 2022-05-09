package com.example.desafio_android_accenture.presentation.viewmodel

import androidx.lifecycle.*
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.domain.GetPullRequestsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullRequestViewModel @Inject constructor(
    private val getPullRequestsUseCase: GetPullRequestsUseCase
) : ViewModel() {

    val pullRequestListState = MutableLiveData<ListState<PullRequestModel>>()

    fun getPullRequests(user: String, repository: String) {
        viewModelScope.launch {
            pullRequestListState.postValue(ListState.Loading())
            val result = getPullRequestsUseCase(user, repository)
            if(result.isEmpty() || !result.isNullOrEmpty()){
                pullRequestListState.postValue(ListState.Success(result))
            }else{
                pullRequestListState.postValue(ListState.Error())
            }
        }
    }

}