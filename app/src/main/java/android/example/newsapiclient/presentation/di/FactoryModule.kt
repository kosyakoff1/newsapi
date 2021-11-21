package android.example.newsapiclient.presentation.di

import android.app.Application
import android.example.newsapiclient.domain.GetNewsHeadlinesUseCase
import android.example.newsapiclient.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Provides
    @Singleton
    fun providesNewsViewModelFactory(
        application: Application,
        newsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    ): NewsViewModelFactory {
        return NewsViewModelFactory(application, newsHeadlinesUseCase)
    }
}