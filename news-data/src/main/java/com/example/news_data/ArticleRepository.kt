package com.example.news_data


import com.example.database.NewsDatabase
import com.example.news_data.models.Article
import com.example.newsapi.NewsApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ArticleRepository(
    private val database: NewsDatabase,
    private val api: NewsApi
) {
    suspend fun getAll(): RequestResult<Flow<List<Article>>> {
         return RequestResult.InProgress(
             database.articlesDao
             .getAll()
             .map { articles -> articles.map{it.toArticle()} }
         )
     }
    suspend fun search(query: String): Flow<Article> {
        api.everything()
        TODO("Not implemented")
    }
}

sealed class RequestResult<E>(protected val data: E?){
    class InProgress<E>(data: E?): RequestResult<E>(data)
    class Success<E>(data: E?): RequestResult<E>(data)
    class Error<E>(data: E?): RequestResult<E>(data)
}