package android.example.newsapiclient.data.entity

import android.example.newsapiclient.data.model.Article
import android.example.newsapiclient.data.model.Source
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "articles")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    @SerializedName("author")
    val author: String?,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String,
) : Serializable {
    fun toModel(): Article {
        return Article(
            id = this.id,
            author = this.author,
            content = this.content,
            description = this.description,
            publishedAt = this.publishedAt,
            source = this.source,
            title = this.title,
            url = this.url,
            urlToImage = this.urlToImage)
    }

    companion object {
        fun fromModel(article: Article): ArticleEntity = ArticleEntity(
            id = article.id,
            author = article.author,
            content = article.content,
            description = article.description,
            publishedAt = article.publishedAt,
            source = article.source,
            title = article.title,
            url = article.url,
            urlToImage = article.urlToImage)
    }
}