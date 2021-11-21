package android.example.newsapiclient.data.repository.datasourceimpl

import android.example.newsapiclient.data.api.NewsAPIService
import android.example.newsapiclient.data.model.APIResponse
import android.example.newsapiclient.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService,
) : NewsRemoteDataSource {
    override suspend fun getTopHeadLines(
        country: String,
        page: Int,
    ): Response<APIResponse> =
        newsAPIService.getTopHeadlines(country, page)

    override suspend fun getSearchedTopHeadLines(
        country: String,
        searchQuery: String,
        page: Int,
    ): Response<APIResponse> = newsAPIService.getSearchedTopHeadlines(country, searchQuery, page)
}