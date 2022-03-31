package com.example.desafio_android_accenture.data

import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.data.network.GithubApiService

class GithubRepository {
    private val api = GithubApiService()

    suspend fun getAllRepositories(page: Int): List<RepositoryModel> {
        return api.getRepositories(page)
    }

    suspend fun getPullRequests(user: String, repository:String): List<PullRequestModel> {
        return api.getPullRequests(user, repository)
    }
}