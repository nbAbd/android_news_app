package my.mvvm.news.di.module

import dagger.Module
import dagger.Provides
import my.mvvm.news.data.remote.api.NewsApi
import my.mvvm.news.data.repository.NewsRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Singleton
    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @Singleton
    @Provides
    fun provideNewsRepository(newsApi: NewsApi): NewsRepository {
        return NewsRepository(newsApi)
    }
}