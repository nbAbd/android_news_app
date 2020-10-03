package my.mvvm.news.di.module

import dagger.Module
import dagger.Provides
import my.mvvm.news.data.repository.NewsRepository
import my.mvvm.news.ui.news_list.ViewModelFactory
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Singleton
    @Provides
    fun provideViewModelFactory(newsRepository: NewsRepository): ViewModelFactory {
        return ViewModelFactory(newsRepository)
    }
}