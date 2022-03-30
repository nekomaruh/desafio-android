package com.example.desafio_android_accenture.data.models

import com.google.gson.annotations.SerializedName

data class RepoModel(
    @SerializedName("title") val title:String,
    @SerializedName("description") val description: String,
    @SerializedName("username") val username: String,
    @SerializedName("user_real_name") val userRealName: String,
    @SerializedName("user_img_url") val userImgUrl: String,
    @SerializedName("repo_url") val repo_url: String,
    @SerializedName("stars") val stars: String,
    @SerializedName("branches") val branches: String,
    @SerializedName("issues_opened") val issuesOpened: String,
    @SerializedName("issues_closed") val issuesClosed: String,
)