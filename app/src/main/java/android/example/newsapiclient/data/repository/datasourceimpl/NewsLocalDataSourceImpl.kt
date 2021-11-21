package android.example.newsapiclient.data.repository.datasourceimpl

import android.example.newsapiclient.data.db.ArticleDao
import android.example.newsapiclient.data.entity.ArticleEntity
import android.example.newsapiclient.data.model.Article
import android.example.newsapiclient.data.repository.datasource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsLocalDataSourceImpl(private val articleDao: ArticleDao) : NewsLocalDataSource {
    override suspend fun saveArticleToDB(article: Article) {
        articleDao.insert(ArticleEntity(author = article.author,
            content = article.content,
            description = article.description,
            publishedAt = article.publishedAt,
            source = article.source,
            title = article.title,
            url = article.url,
            urlToImage = article.urlToImage))
    }

    override fun getSavedArticles(): Flow<List<Article>> {
        return articleDao.getAllArticles()
            .map { it.map { articleEntity -> articleEntity.toModel() } }
    }

    override suspend fun deleteArticleFromDB(articleEntity: ArticleEntity) {
        articleDao.deleteArticle(articleEntity)
    }
}