package android.example.newsapiclient.data.db

import android.example.newsapiclient.data.entity.ArticleEntity
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ArticleEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ArticleDataBase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao
}