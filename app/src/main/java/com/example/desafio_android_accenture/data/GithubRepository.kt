package com.example.desafio_android_accenture.data

import android.util.Log
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.data.model.RepositoryProvider
import com.example.desafio_android_accenture.data.network.GithubApiService

class GithubRepository {
    private val api = GithubApiService()

    suspend fun getAllRepositories(page:Int):List<RepositoryModel>{
        val response = api.getRepositories(page)
        Log.i("TAG",response[0].toString())
        RepositoryProvider.repositories = response
        return response
    }
}