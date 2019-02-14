package at.kkcoding.android.epoxypaginationsample.application

import at.kkcoding.android.epoxypaginationsample.BuildConfig
import at.kkcoding.android.epoxypaginationsample.application.di.AppInjector
import at.kkcoding.android.epoxypaginationsample.application.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber


class PaginationApp : DaggerApplication() {

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder().application(this).build()
  }

  override fun onCreate() {
    super.onCreate()
    AppInjector.init(this)

    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }
}