package android.example.newsapiclient.data.repository

import android.example.newsapiclient.data.entity.ArticleEntity
import android.example.newsapiclient.data.model.APIResponse
import android.example.newsapiclient.data.model.Article
import android.example.newsapiclient.data.repository.datasource.NewsLocalDataSource
import android.example.newsapiclient.data.repository.datasource.NewsRemoteDataSource
import android.example.newsapiclient.data.util.Resource
import android.example.newsapiclient.domain.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource,
    private val newsLocalDataSource: NewsLocalDataSource,
) : NewsRepository {

    private fun responseToResult(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getNewsHeadlines(
        country: String,
        page: Int,
    ): Resource<APIResponse> {
        return responseToResult(newsRemoteDataSource.getTopHeadLines(country = country,
            page = page))
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int,
    ): Resource<APIResponse> {
        return responseToResult(newsRemoteDataSource.getSearchedTopHeadLines(country,
            searchQuery,
            page))
    }

    override suspend fun saveNews(article: Article) {
        newsLocalDataSource.saveArticleToDB(article)
    }

    override suspend fun deleteNews(article: Article) {
        newsLocalDataSource.deleteArticleFromDB(ArticleEntity.fromModel(article))
    }

    override fun getSavedNews(): Flow<List<Article>> = newsLocalDataSource.getSavedArticles()
}