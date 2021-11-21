package android.example.newsapiclient.presentation.di

import android.app.Application
import android.example.newsapiclient.data.db.ArticleDao
import android.example.newsapiclient.data.db.ArticleDataBase
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Provides
    @Singleton
    fun providesNewsDatabase(app: Application): ArticleDataBase {
        return Room.databaseBuilder(app, ArticleDataBase::class.java, "news_db")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesNewsDao(articleDataBase: ArticleDataBase): ArticleDao {
        return articleDataBase.getArticleDao()
    }
}