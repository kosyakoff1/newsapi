package android.example.newsapiclient.domain

import android.example.newsapiclient.data.model.APIResponse
import android.example.newsapiclient.data.util.Resource

class GetNewsHeadlinesUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(
        country: String,
        page: Int,
    ): Resource<APIResponse> {
        return newsRepository.getNewsHeadlines(country, page)
    }
}