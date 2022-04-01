package com.example.desafio_android_accenture.data.network

import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.data.model.RepositoryResponse
import retrofit2.Response
import retrofit2.http.*

interface GithubApiClient {

    @GET("search/repositories?q=language:Java&sort=stars")
    suspend fun getAllRepositories(@Query("page") page:Int): Response<RepositoryResponse>

    //@GET("users/{user}")
    //suspend fun getUser(@Path("user") user:String): Response<List<UserModel>>

    @GET("repos/{user}/{repo}/pulls")
    suspend fun getPullRequests(
        @Path("user") user:String,
        @Path("repo") repository: String)
    : Response<List<PullRequestModel>>
}