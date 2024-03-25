package com.example.newsapi.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class ArticleDTO(
    @SerialName("source")val source: SourceDTO,
    @SerialName("author")val author: String,
    @SerialName("description")val description: String,
    @SerialName("url")val url: String,
    @SerialName("urlToImage")val urlToImage: String,
    @SerialName("publishedAt")val publishedAt: Date,
    @SerialName("content")val content: String,
)