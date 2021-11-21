package android.example.newsapiclient.domain

import android.example.newsapiclient.data.model.APIResponse
import android.example.newsapiclient.data.util.Resource

class GetSearchedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(country: String, searchQuery: String, page: Int): Resource<APIResponse> {
        return newsRepository.getSearchedNews(country, searchQuery, page)
    }
}