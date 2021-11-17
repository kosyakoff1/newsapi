package android.example.newsapiclient.domain

import android.example.newsapiclient.data.model.Article

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.deleteNews(article)
}