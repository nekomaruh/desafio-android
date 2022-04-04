package com.example.desafio_android_accenture.data

import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.data.network.GithubApiService
import javax.inject.Inject

class GithubRepository @Inject constructor(private val githubApiService: GithubApiService){
    //private val api = GithubApiService()

    suspend fun getAllRepositories(page: Int): List<RepositoryModel> {
        return githubApiService.getRepositories(page)
    }

    suspend fun getPullRequests(user: String, repository:String): List<PullRequestModel> {
        return githubApiService.getPullRequests(user, repository)
    }
}