package com.example.newsapi

import com.example.newsapi.models.ArticleDTO
import com.example.newsapi.models.Language
import com.example.newsapi.models.ResponseDTO
import com.example.newsapi.models.SortBy
import com.example.newsapi.utils.NewsApiKeyInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

interface NewsApi {
    @GET("/everything")
    suspend fun everything(
        @Query("q") query: String? = null,
        @Query("from") from: Date? = null,
        @Query("to") to: Date? = null,
        @Query("language") language: List<Language>? = null,
        @Query("sortBy") sortBy: SortBy? = null,
        @Query("pageSize") pageSize: Int = 100,
        @Query("page") page: Int = 1
    ): Result<ResponseDTO<ArticleDTO>>
}

fun NewsApi(
    baseUrl: String,
    okHttpClient: OkHttpClient? = null,
    json: Json = Json,
    apiKey: String
): NewsApi {
    return retrofit(baseUrl, okHttpClient, json, apiKey).create()

}

private fun retrofit(
    baseUrl: String,
    okHttpClient: OkHttpClient?,
    json: Json,
    apiKey: String
): Retrofit {
    val jsonConverterFactory = json.asConverterFactory(MediaType.get("application/json"))

    val modifiedOkhttpClient = (okHttpClient?.newBuilder() ?: OkHttpClient.Builder())
        .addInterceptor(NewsApiKeyInterceptor(apiKey))
        .build()

    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(jsonConverterFactory)
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .client(modifiedOkhttpClient)
        .build()
}
