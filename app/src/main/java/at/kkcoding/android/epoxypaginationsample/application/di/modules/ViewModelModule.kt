package at.kkcoding.android.epoxypaginationsample.application.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import at.kkcoding.android.epoxypaginationsample.application.di.ViewModelFactory
import at.kkcoding.android.epoxypaginationsample.application.di.helpers.ViewModelKey
import at.kkcoding.android.epoxypaginationsample.features.feed.FeedViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module()
interface ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(FeedViewModel::class)
  fun bindFeedViewModel(viewModel: FeedViewModel): ViewModel

  @Binds
  fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}