package com.example.desafio_android_accenture.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio_android_accenture.data.models.RepoModel
import com.example.desafio_android_accenture.data.models.RepoProvider

class MainViewModel: ViewModel() {
    val repoModel = MutableLiveData<List<RepoModel>>()

    fun getRepoList(){
        repoModel.postValue(RepoProvider.getRepositories())
    }
}