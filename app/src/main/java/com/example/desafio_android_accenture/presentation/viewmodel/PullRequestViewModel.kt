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

    // LiveData
    private val pullRequestList = MutableLiveData<List<PullRequestModel>>()

    // State
    val pullRequestListState = MutableLiveData<ListState>()

    fun getPullRequests(user: String, repository: String) {
        viewModelScope.launch {
            pullRequestListState.postValue(ListState.Loading)
            val result = getPullRequestsUseCase(user, repository)

            if (result.isEmpty()) {
                pullRequestList.postValue(emptyList())
                pullRequestListState.postValue(ListState.NoData)
            } else if (!result.isNullOrEmpty()) {
                pullRequestList.postValue(result)
                pullRequestListState.postValue(ListState.Success(result))
            } else {
                pullRequestList.postValue(emptyList())
                pullRequestListState.postValue(ListState.Error)
            }
        }
    }

    fun clearList() = pullRequestList.postValue(emptyList())
}