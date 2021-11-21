package android.example.newsapiclient.data.db

import android.example.newsapiclient.data.entity.ArticleEntity
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(articleEntity: ArticleEntity)

    @Query("Select * from articles")
    fun getAllArticles(): Flow<List<ArticleEntity>>

    @Delete
    suspend fun deleteArticle(articleEntity: ArticleEntity)
}