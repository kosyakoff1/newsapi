package android.example.newsapiclient.presentation.di

import android.example.newsapiclient.data.repository.NewsRepositoryImpl
import android.example.newsapiclient.data.repository.datasource.NewsLocalDataSource
import android.example.newsapiclient.data.repository.datasource.NewsRemoteDataSource
import android.example.newsapiclient.domain.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource,
        newsLocalDataSource: NewsLocalDataSource,
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource, newsLocalDataSource)
    }
}