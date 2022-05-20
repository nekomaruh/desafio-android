package com.example.desafio_android_accenture.domain

import com.example.desafio_android_accenture.data.GithubRepository
import com.example.desafio_android_accenture.data.model.RepositoryModel
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(private val repository: GithubRepository) {
    suspend operator fun invoke(page: Int): List<RepositoryModel> =
        repository.getAllRepositories(page)
}