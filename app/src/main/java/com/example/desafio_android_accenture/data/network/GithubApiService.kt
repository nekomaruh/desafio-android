package com.example.desafio_android_accenture.data.network

import android.util.Log
import com.example.desafio_android_accenture.data.model.PullRequestModel
import com.example.desafio_android_accenture.data.model.RepositoryModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GithubApiService @Inject constructor(private val api: GithubApiClient){


    suspend fun getRepositories(page:Int):List<RepositoryModel>{
        // Executes in a secondary thread to avoid UI freeze
        return withContext(Dispatchers.IO){
            val response = api.getAllRepositories(page)
            //Log.i("JSON:",response.body().toString())
            response.body()?.repositories ?: emptyList()
        }
    }

    suspend fun getPullRequests(user:String, repository:String):List<PullRequestModel>{
        // Executes in a secondary thread to avoid UI freeze
        return withContext(Dispatchers.IO){
            val response = api.getPullRequests(user, repository)
            Log.i("JSON:",response.body().toString())
            response.body() ?: emptyList()
        }
    }
}