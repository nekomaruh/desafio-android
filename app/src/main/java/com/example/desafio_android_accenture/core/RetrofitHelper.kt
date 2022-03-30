package com.example.desafio_android_accenture.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("asd")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}