package com.example.desafio_android_accenture.data.network

import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.data.model.RepositoryModel

interface GithubRepository{
    suspend fun getAllRepositories(page: Int): List<RepositoryModel>
    suspend fun getPullRequests(user: String, repository:String): List<PullRequestModel>
}