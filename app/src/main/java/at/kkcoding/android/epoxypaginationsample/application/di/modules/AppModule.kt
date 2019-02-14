package at.kkcoding.android.epoxypaginationsample.application.di.modules

import android.app.Application
import android.content.Context
import at.kkcoding.android.epoxypaginationsample.BuildConfig
import at.kkcoding.android.epoxypaginationsample.api.RedditApi
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(
  includes = [
    ViewModelModule::class,
    AppModule.Bindings::class
  ]
)
class AppModule {

  @Module
  interface Bindings {
    @Binds
    fun context(app: Application): Context
  }

  @Singleton
  @Provides
  fun provideMoshi(): Moshi {
    return Moshi.Builder()
      .build()
  }

  @Singleton
  @Provides
  fun provideOkHttp(): OkHttpClient {
    return OkHttpClient.Builder()
      .apply {
        if (BuildConfig.DEBUG) {
          addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
      }
      .build()
  }

  @Singleton
  @Provides
  fun provideRedditAPI(okHttp: OkHttpClient, moshi: Moshi): RedditApi {
    return Retrofit.Builder()
      .baseUrl("https://www.reddit.com/r/")
      .client(okHttp)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
      .build()
      .create(RedditApi::class.java)
  }
}