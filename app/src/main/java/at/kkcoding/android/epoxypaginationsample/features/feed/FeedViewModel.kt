package at.kkcoding.android.epoxypaginationsample.features.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import at.kkcoding.android.epoxypaginationsample.datasource.RedditFeedDataSourceFactory
import at.kkcoding.android.epoxypaginationsample.model.reddit.RedditChildResponse
import timber.log.Timber
import javax.inject.Inject

class FeedViewModel @Inject constructor(
  sourceFactory: RedditFeedDataSourceFactory
) : ViewModel() {

  val feed: LiveData<PagedList<RedditChildResponse>>

  init {
    feed = LivePagedListBuilder<String, RedditChildResponse>(sourceFactory, getPagedListConfig())
      .setBoundaryCallback(object : PagedList.BoundaryCallback<RedditChildResponse>() {
        override fun onItemAtEndLoaded(itemAtEnd: RedditChildResponse) {
          super.onItemAtEndLoaded(itemAtEnd)
          Timber.v("reached end of feed")
        }
      })
      .build()
  }

  fun reload() {
    feed.value?.dataSource?.invalidate()
  }

  private fun getPagedListConfig(): PagedList.Config {
    return PagedList.Config.Builder()
      .setEnablePlaceholders(false)
      .setInitialLoadSizeHint(5)
      .setPageSize(20)
      .build()
  }
}
