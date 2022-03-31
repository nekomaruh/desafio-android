package com.example.desafio_android_accenture.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse (
    @SerializedName("total_count") val totalCount:String,
    @SerializedName("incomplete_results") val incompleteResults:String,
    @SerializedName("items") val repositories:List<RepositoryModel>,
)