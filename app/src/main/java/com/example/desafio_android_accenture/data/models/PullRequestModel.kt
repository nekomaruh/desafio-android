package com.example.desafio_android_accenture.data.models

import com.google.gson.annotations.SerializedName

data class PullRequestModel(
    @SerializedName("title") val title:String,
    @SerializedName("body") val body: String,
    @SerializedName("username") val username: String,
    @SerializedName("user_real_name") val userRealName: String,
    @SerializedName("user_img_url") val userImgUrl: String,
    @SerializedName("pull_request_url") val repo_url: String,
)
