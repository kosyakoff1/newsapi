package android.example.newsapiclient.presentation.di

import android.example.newsapiclient.data.db.ArticleDao
import android.example.newsapiclient.data.repository.datasource.NewsLocalDataSource
import android.example.newsapiclient.data.repository.datasourceimpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Provides
    @Singleton
    fun providesLocalDataSource(articleDao: ArticleDao): NewsLocalDataSource {
        return NewsLocalDataSourceImpl(articleDao)
    }
}