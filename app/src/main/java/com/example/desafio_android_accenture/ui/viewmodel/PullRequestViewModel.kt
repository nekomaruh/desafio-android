package com.example.desafio_android_accenture.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.domain.GetPullRequestsUseCase
import kotlinx.coroutines.launch

class PullRequestViewModel: ViewModel() {
    // Use Cases
    var getPullRequestsUseCase = GetPullRequestsUseCase()

    // Live Data
    val pullRequestList = MutableLiveData<List<PullRequestModel>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(user:String, repository:String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getPullRequestsUseCase(user,repository)

            if(!result.isNullOrEmpty()){
                isLoading.postValue(false)
                pullRequestList.postValue(result)
            }
        }
    }

    //fun getPullRequests(): MutableLiveData<List<PullRequestModel>> = pullRequestList
    //fun getIsLoading(): MutableLiveData<Boolean> = isLoading

}