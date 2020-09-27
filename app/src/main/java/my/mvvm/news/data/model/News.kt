package my.mvvm.news.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class News(
    @Expose
    @SerializedName("status") val status: String,
    @Expose
    @SerializedName("totalResults") val totalResults: Int,
    @Expose
    @SerializedName("articles") val articles: List<Article>
)