package my.mvvm.news.ui.news_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import my.mvvm.news.data.model.Article
import my.mvvm.news.data.repository.NewsRepository
import my.mvvm.news.utils.Constants
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private var currentQueryParamsValue: MutableMap<String, String>? = null
    private var currentNewsRequestResult: Flow<PagingData<Article>>? = null

    fun getNewsResult(): Flow<PagingData<Article>> {
        val queryMap = mutableMapOf<String, String>()
        queryMap["country"] = Constants.country
        queryMap["category"] = Constants.category
        queryMap["apiKey"] = Constants.apiKey

        val lastNewsResult = currentNewsRequestResult
        if (queryMap == currentQueryParamsValue && lastNewsResult != null) {
            return lastNewsResult
        }
        currentQueryParamsValue = queryMap
        val newResult: Flow<PagingData<Article>> = repository.getNewsResultStream(queryMap)
            .cachedIn(viewModelScope)
        currentNewsRequestResult = newResult
        return newResult
    }

}