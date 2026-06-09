package com.example.there_help.data.model

data class BeritaResponse(
    val status: String?,
    val code: String?,
    val message: String?,
    val totalResults: Int?,
    val articles: List<BeritaModel>?
)

data class BeritaModel(
    val title: String?,
    val description: String?,
    val urlToImage: String?
)