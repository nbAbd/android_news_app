package my.mvvm.news.data.remote.api

import my.mvvm.news.data.model.News
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface NewsApi {

    @GET("top-headlines")
    suspend fun getNews(@QueryMap(encoded = true) params: Map<String, String>): News
}