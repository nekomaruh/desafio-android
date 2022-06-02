package com.example.desafio_android_accenture.data.network

import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.data.model.RepositoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubRepositoryImpl(private val api: GithubApiClient): GithubRepository {
    override suspend fun getAllRepositories(page: Int): List<RepositoryModel> {
        return withContext(Dispatchers.IO){
            val response = api.getAllRepositories(page)
            response.body()?.repositories ?: emptyList()
        }
    }

    override suspend fun getPullRequests(user: String, repository: String): List<PullRequestModel> {
        return withContext(Dispatchers.IO){
            val response = api.getPullRequests(user, repository)
            response.body() ?: emptyList()
        }
    }

}