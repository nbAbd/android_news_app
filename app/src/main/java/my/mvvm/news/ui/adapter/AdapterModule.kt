package my.mvvm.news.ui.adapter

import dagger.Module
import dagger.Provides
import my.mvvm.news.ui.adapter.news.NewsAdapter

@Module
class AdapterModule {
    @Provides
    fun provideNewsAdapter(): NewsAdapter {
        return NewsAdapter()
    }
}