package my.mvvm.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import my.mvvm.news.data.model.Article
import my.mvvm.news.data.paging.NewsPagingSource
import my.mvvm.news.data.remote.api.NewsApi
import my.mvvm.news.utils.Constants

class NewsRepository(private val newsApi: NewsApi) {

    fun getNewsResultStream(queryMap: MutableMap<String, String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    queryParams = queryMap
                )
            }
        ).flow
    }
}