package my.mvvm.news.data.paging

import androidx.paging.PagingSource
import my.mvvm.news.data.model.Article
import my.mvvm.news.data.remote.api.NewsApi
import my.mvvm.news.utils.Constants
import retrofit2.HttpException
import timber.log.Timber

class NewsPagingSource(
    private val newsApi: NewsApi,
    private var queryParams: MutableMap<String, String>
) :
    PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val currentPosition = params.key ?: Constants.STARTING_PAGE_INDEX
        val pageSize = params.loadSize
        if (currentPosition == 1) {
            val initialPageSize = 5
            queryParams["pageSize"] = initialPageSize.toString()
        } else {
            queryParams["pageSize"] = pageSize.toString()
        }
        queryParams["page"] = currentPosition.toString()
        return try {
            Timber.e(queryParams.toString())
            val response = newsApi.getNews(queryParams)
            val articles = response.articles
            LoadResult.Page(
                data = articles,
                prevKey = if (currentPosition == Constants.STARTING_PAGE_INDEX) null else currentPosition - 1,
                nextKey = if (articles.isEmpty()) null else currentPosition + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }

    }


}