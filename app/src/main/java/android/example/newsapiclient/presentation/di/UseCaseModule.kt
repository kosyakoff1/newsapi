package android.example.newsapiclient.presentation.di

import android.example.newsapiclient.domain.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providesNewsHeadlinesUseCase(newsRepository: NewsRepository): GetNewsHeadlinesUseCase {
        return GetNewsHeadlinesUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun providesSearchedNewsHeadlinesUseCase(newsRepository: NewsRepository): GetSearchedNewsUseCase {
        return GetSearchedNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun providesSaveNewsUseCase(newsRepository: NewsRepository): SaveNewsUseCase {
        return SaveNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun providesGetSavedNewsUseCase(newsRepository: NewsRepository): GetSavedNewsUseCase {
        return GetSavedNewsUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun providesDeleteSavedNewsUseCase(newsRepository: NewsRepository): DeleteSavedNewsUseCase {
        return DeleteSavedNewsUseCase(newsRepository)
    }
}