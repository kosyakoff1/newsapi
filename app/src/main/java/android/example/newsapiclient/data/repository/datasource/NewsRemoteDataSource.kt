package android.example.newsapiclient.data.repository.datasource

import android.example.newsapiclient.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadLines(): Response<APIResponse>
}