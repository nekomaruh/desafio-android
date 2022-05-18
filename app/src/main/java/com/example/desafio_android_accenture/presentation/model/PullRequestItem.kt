package com.example.desafio_android_accenture.presentation.model
import com.example.desafio_android_accenture.data.model.UserModel

data class PullRequestItem(
    val title: String,
    val body: String,
    val userImgUrl: String,
    val repo_url: String,
    val createdAt: String,
    val user: UserModel,
){
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as PullRequestItem
        if (title != other.title) return false
        if (body != other.body) return false
        if (repo_url != other.repo_url) return false
        if (createdAt != other.createdAt) return false
        if (userImgUrl != other.userImgUrl) return false
        if (user != other.user) return false
        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + body.hashCode()
        result = 31 * result + userImgUrl.hashCode()
        result = 31 * result + repo_url.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + user.hashCode()
        return result
    }
}