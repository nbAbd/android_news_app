package my.mvvm.news.di.module

import dagger.Module
import dagger.Provides
import my.mvvm.news.data.remote.api.NewsApi
import my.mvvm.news.data.repository.NewsRepository
import retrofit2.Retrofit

@Module
class ApiModule {

    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @Provides
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepository(newsApi)
    }
}