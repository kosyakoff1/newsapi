package android.example.newsapiclient.data.repository.datasourceimpl

import android.example.newsapiclient.data.api.NewsAPIService
import android.example.newsapiclient.data.model.APIResponse
import android.example.newsapiclient.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val country: String,
    private val page: Int,
    private val newsAPIService: NewsAPIService,
) : NewsRemoteDataSource {
    override suspend fun getTopHeadLines(): Response<APIResponse> =
        newsAPIService.getTopHeadlines(country, page)
}