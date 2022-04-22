package com.example.desafio_android_accenture.presentation.model
import com.example.desafio_android_accenture.data.model.UserModel

data class RepositoryItem(
    val name: String,
    val fullName: String,
    val description: String,
    val repo_url: String,
    val issuesOpened: String,
    val stars: String,
    val branches: String,
    val userImgUrl: String,
    val user: UserModel
)