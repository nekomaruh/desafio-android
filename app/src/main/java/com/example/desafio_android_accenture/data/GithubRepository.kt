package com.example.desafio_android_accenture.data

import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.data.network.GithubApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GithubRepository @Inject constructor(private val api: GithubApiClient){

    suspend fun getAllRepositories(page: Int): List<RepositoryModel> {
        return withContext(Dispatchers.IO){
            val response = api.getAllRepositories(page)
            response.body()?.repositories ?: emptyList()
        }
    }

    suspend fun getPullRequests(user: String, repository:String): List<PullRequestModel> {
        return withContext(Dispatchers.IO){
            val response = api.getPullRequests(user, repository)
            response.body() ?: emptyList()
        }
    }
}