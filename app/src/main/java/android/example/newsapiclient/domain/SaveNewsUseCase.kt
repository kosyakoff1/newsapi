package android.example.newsapiclient.domain

import android.example.newsapiclient.data.model.Article

class SaveNewsUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(article: Article) = newsRepository.saveNews(article)
}