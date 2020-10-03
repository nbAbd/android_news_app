package my.mvvm.news.ui.news_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.mvvm.news.data.repository.NewsRepository
import javax.inject.Singleton

@Singleton
class ViewModelFactory(private val newsRepository: NewsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(newsRepository) as T
        }
        throw IllegalArgumentException("Unknown vieModel class")
    }
}