package com.example.news_main

import com.example.news_data.ArticleRepository
import com.example.news_data.models.Article
import kotlinx.coroutines.flow.Flow

class GetAllArticlesUseCase(private val repository: ArticleRepository) {
    operator fun invoke(): Flow<Article> {
     return repository.getAll()
    }
}