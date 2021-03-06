package android.example.newsapiclient.data.repository.datasource

import android.example.newsapiclient.data.entity.ArticleEntity
import android.example.newsapiclient.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveArticleToDB(article: Article)

    fun getSavedArticles(): Flow<List<Article>>

    suspend fun deleteArticleFromDB(articleEntity: ArticleEntity)
}