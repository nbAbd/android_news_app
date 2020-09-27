package my.mvvm.news.di

import dagger.Component
import my.mvvm.news.di.module.ApiModule
import my.mvvm.news.di.module.AppModule
import my.mvvm.news.di.module.NetworkModule
import my.mvvm.news.di.module.ViewModelFactoryModule
import my.mvvm.news.ui.MainViewModelModule
import my.mvvm.news.ui.adapter.AdapterModule
import my.mvvm.news.ui.news_list.NewsListActivity

@Component(
    modules = [AppModule::class,
        NetworkModule::class,
        MainViewModelModule::class,
        ApiModule::class,
        AdapterModule::class,
        MainViewModelModule::class,
        ViewModelFactoryModule::class]
)
interface AppComponent {
    fun inject(activity: NewsListActivity)
}