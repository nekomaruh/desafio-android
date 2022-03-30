package com.example.desafio_android_accenture.data.models

class PullRequestProvider {
    companion object{
        fun getPullRequests(): List<PullRequestModel> {
            return pullRequests;
        }

        private val pullRequests = listOf<PullRequestModel>(
            PullRequestModel(
                title = "Repo Name",
                body = "Body 1 pull req",
                username = "neko",
                userRealName = "Johan",
                userImgUrl = "asd",
                repo_url = "url",
            ),
            PullRequestModel(
                title = "Repo Name 2",
                body = "Body 2 pull req",
                username = "neko",
                userRealName = "Johan",
                userImgUrl = "asd",
                repo_url = "url",
            )
        )
    }
}