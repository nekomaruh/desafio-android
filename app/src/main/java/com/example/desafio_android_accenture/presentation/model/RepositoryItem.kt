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
) {
    override fun equals(other: Any?): Boolean {
        if (javaClass != other?.javaClass) return false
        other as RepositoryItem
        if (name != other.name) return false
        if (fullName != other.fullName) return false
        if (description != other.description) return false
        if (repo_url != other.repo_url) return false
        if (issuesOpened != other.issuesOpened) return false
        if (stars != other.stars) return false
        if (branches != other.branches) return false
        if (userImgUrl != other.userImgUrl) return false
        if (user != other.user) return false
        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + fullName.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + repo_url.hashCode()
        result = 31 * result + issuesOpened.hashCode()
        result = 31 * result + stars.hashCode()
        result = 31 * result + branches.hashCode()
        result = 31 * result + userImgUrl.hashCode()
        result = 31 * result + user.hashCode()
        return result
    }
}