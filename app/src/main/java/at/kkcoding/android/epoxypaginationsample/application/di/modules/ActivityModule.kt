package at.kkcoding.android.epoxypaginationsample.application.di.modules

import at.kkcoding.android.epoxypaginationsample.application.di.helpers.PerActivity
import at.kkcoding.android.epoxypaginationsample.features.feed.FeedActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

  @PerActivity
  @ContributesAndroidInjector()
  fun provideFeedActivity(): FeedActivity

}