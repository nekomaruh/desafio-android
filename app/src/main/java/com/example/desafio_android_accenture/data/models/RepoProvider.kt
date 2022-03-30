package com.example.desafio_android_accenture.data.models

class RepoProvider {
    companion object{
        fun getRepositories(): List<RepoModel> {
            return repos;
        }

        private val repos = listOf<RepoModel>(
            RepoModel(
                title = "Repo Name",
                description = "description",
                username = "neko",
                userRealName = "Johan",
                userImgUrl = "asd",
                repo_url = "url",
                branches = "4",
                stars = "3",
                issuesOpened = "1",
                issuesClosed = "2"
            ),
            RepoModel(
                title = "Repo Name 2",
                description = "description",
                username = "neko",
                userRealName = "Johan",
                userImgUrl = "asd",
                repo_url = "url",
                branches = "23",
                stars = "830",
                issuesOpened = "233",
                issuesClosed = "34"
            )
        )
    }
}