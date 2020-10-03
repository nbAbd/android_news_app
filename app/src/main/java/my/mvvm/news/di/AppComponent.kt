package my.mvvm.news.di

import dagger.Component
import my.mvvm.news.di.module.ApiModule
import my.mvvm.news.di.module.AppModule
import my.mvvm.news.di.module.NetworkModule
import my.mvvm.news.di.module.ViewModelModule
import my.mvvm.news.ui.adapter.AdapterModule
import my.mvvm.news.ui.news_list.NewsListActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ApiModule::class,
        AdapterModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun inject(activity: NewsListActivity)
}