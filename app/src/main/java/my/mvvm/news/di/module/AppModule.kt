package my.mvvm.news.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val mApplication: Application) {

    @Provides
    fun provideContext(): Context = mApplication
}