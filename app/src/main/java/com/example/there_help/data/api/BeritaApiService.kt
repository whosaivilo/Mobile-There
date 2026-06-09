package com.example.there_help.data.api

import com.example.there_help.data.model.BeritaResponse
import retrofit2.http.GET

interface BeritaApiService {
    @GET("v2/everything?q=indonesia&apiKey=e6f343cd7cf04f6f8b7347b981f4cd49")
    suspend fun getBerita(): BeritaResponse
}