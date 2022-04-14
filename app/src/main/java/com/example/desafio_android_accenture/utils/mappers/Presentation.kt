package com.example.desafio_android_accenture.utils.mappers

import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.presentation.model.PullRequestItem
import com.example.desafio_android_accenture.utils.extensions.parseISO8601DateToString

fun PullRequestModel.toPullRequestItem() = PullRequestItem(
    title = title,
    body = body ?: "",
    userImgUrl = userImgUrl,
    repo_url = repo_url,
    createdAt = createdAt.parseISO8601DateToString(),
    user = user
)