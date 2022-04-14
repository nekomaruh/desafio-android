package com.example.desafio_android_accenture.presentation.model

import com.example.desafio_android_accenture.data.model.UserModel

data class PullRequestItem(
    val title: String,
    val body: String,
    val userImgUrl: String,
    val repo_url: String,
    val createdAt: String,
    val user: UserModel
)