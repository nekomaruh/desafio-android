package com.example.desafio_android_accenture.utils.mappers

import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.data.model.RepositoryModel
import com.example.desafio_android_accenture.presentation.model.PullRequestItem
import com.example.desafio_android_accenture.presentation.model.RepositoryItem
import com.example.desafio_android_accenture.utils.extensions.parseISO8601DateToString

fun RepositoryModel.toRepositoryItem() = RepositoryItem(
    name = name,
    fullName = fullName,
    description = description ?: "",
    repo_url = repo_url,
    issuesOpened = issuesOpened,
    stars = stars,
    branches = branches,
    userImgUrl = userImgUrl ?: "https://demofree.sirv.com/nope-not-here.jpg",
    user = user
)

fun PullRequestModel.toPullRequestItem() = PullRequestItem(
    title = title,
    body = body ?: "",
    userImgUrl = userImgUrl ?: "",
    repo_url = repo_url ?: "",
    createdAt = createdAt.parseISO8601DateToString(),
    user = user
)