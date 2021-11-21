package android.example.newsapiclient.data.repository.datasource

import android.example.newsapiclient.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadLines(
        country: String,
        page: Int,
    ): Response<APIResponse>

    suspend fun getSearchedTopHeadLines(
        country: String,
        searchQuery: String,
        page: Int,
    ): Response<APIResponse>
}