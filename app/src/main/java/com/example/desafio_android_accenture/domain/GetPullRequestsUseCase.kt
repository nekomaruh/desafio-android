package com.example.desafio_android_accenture.domain

import com.example.desafio_android_accenture.data.GithubRepository
import com.example.desafio_android_accenture.data.model.PullRequestModel

class GetPullRequestsUseCase {
    private val repository = GithubRepository()

    suspend operator fun invoke(user:String, repo:String):List<PullRequestModel> = repository.getPullRequests(user, repo)
}