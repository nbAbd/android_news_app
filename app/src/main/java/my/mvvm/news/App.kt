package my.mvvm.news

import android.app.Application
import android.content.Context
import androidx.databinding.library.BuildConfig
import my.mvvm.news.di.AppComponent
import my.mvvm.news.di.DaggerAppComponent
import my.mvvm.news.di.module.AppModule
import my.mvvm.news.di.module.NetworkModule
import my.mvvm.news.utils.Constants
import timber.log.Timber

class App : Application() {
    companion object {
        fun get(context: Context): App = context.applicationContext as App
    }

    private lateinit var mComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        mComponent = initAppComponent()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initAppComponent() =
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(Constants.BASE_URL))
            .build()

    fun getAppComponent(): AppComponent = mComponent
}