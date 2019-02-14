package at.kkcoding.android.epoxypaginationsample.application.di

import android.app.Application
import at.kkcoding.android.epoxypaginationsample.application.PaginationApp
import at.kkcoding.android.epoxypaginationsample.application.di.modules.ActivityModule
import at.kkcoding.android.epoxypaginationsample.application.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
  modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityModule::class
  ]
)
interface AppComponent : AndroidInjector<PaginationApp> {

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }
}