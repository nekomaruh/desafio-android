package com.example.desafio_android_accenture.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.domain.GetPullRequestsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PullRequestViewModel @Inject constructor(
    private val getPullRequestsUseCase: GetPullRequestsUseCase
): ViewModel() {

    // Live Data
    val pullRequestList = MutableLiveData<List<PullRequestModel>>()
    val isListEmpty = MutableLiveData<Boolean>()
    val isLoading = MutableLiveData<Boolean>()

    fun getPullRequests(user:String, repository:String){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getPullRequestsUseCase(user,repository)

            if(!result.isNullOrEmpty()){
                pullRequestList.postValue(result)
            }else{
                isListEmpty.postValue(true)
            }
            isLoading.postValue(false)
        }
    }
}