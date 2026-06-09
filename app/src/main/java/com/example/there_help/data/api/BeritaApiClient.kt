package com.example.there_help.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BeritaApiClient {
    private const val BASE_URL = "https://newsapi.org/"

    val apiService: BeritaApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeritaApiService::class.java)
    }
}