package my.mvvm.news.di.module

import android.content.Context
import androidx.databinding.library.baseAdapters.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import my.mvvm.news.utils.AppUtils
import my.mvvm.news.utils.Constants
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule(private val mApiUrl: String) {

    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return httpLoggingInterceptor
    }

    /***
     * Interceptor which is responsible for observing and modifying
     * requests going out and the corresponding responses coming back in.
     * -> addInterceptor
     */

    @Provides
    internal fun provideOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, Constants.cashSize))
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (AppUtils.hasNetwork(context))

                /**
                 *  If there is Internet, get the cache that was stored 5 seconds ago.
                 *  If the cache is older than 5 seconds, then discard it,
                 *  and indicate an error in fetching the response.
                 *  The 'max-age' attribute is responsible for this behavior.
                 */
                    request.newBuilder().header("Cache-Control", "max-age=" + 5).build()
                else
                /**
                 *  If there is no Internet, get the cache that was stored 5 days ago.
                 *  If the cache is older than 7 days, then discard it,
                 *  and indicate an error in fetching the response.
                 *  The 'max-stale' attribute is responsible for this behavior.
                 *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
                 */
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 5
                    ).build()

                // add modified request to the chain
                chain.proceed(request)
            }.build()
    }

    @Provides
    internal fun provideGson(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    // @TODO set cashing functionality

    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(mApiUrl)
            .build()
    }
}