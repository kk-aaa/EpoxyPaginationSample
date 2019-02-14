package at.kkcoding.android.epoxypaginationsample.datasource

import androidx.paging.PageKeyedDataSource
import at.kkcoding.android.epoxypaginationsample.api.RedditApi
import at.kkcoding.android.epoxypaginationsample.model.reddit.RedditChildResponse
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class PageKeyedFeedDataSource(private val redditApi: RedditApi) :
  PageKeyedDataSource<String, RedditChildResponse>() {

  override fun loadInitial(
    params: LoadInitialParams<String>, callback: LoadInitialCallback<String, RedditChildResponse>
  ) {
    redditApi.fetchNew(subreddit, limit = 10)
      .subscribeOn(Schedulers.io())
      .map { it.data }
      .subscribe({
        callback.onResult(it.children, it.before, it.after)
      }, {
        Timber.e(it)
        callback.onResult(emptyList(), "", "")
      })
  }

  override fun loadAfter(
    params: LoadParams<String>, callback: LoadCallback<String, RedditChildResponse>
  ) {
    redditApi.fetchNew(subreddit, limit = 10, after = params.key)
      .subscribeOn(Schedulers.io())
      .map { it.data }
      .subscribe({
        callback.onResult(it.children, it.after)
      }, {
        Timber.e(it)
        callback.onResult(emptyList(), "")
      })
  }

  override fun loadBefore(
    params: LoadParams<String>, callback: LoadCallback<String, RedditChildResponse>
  ) = Unit

  companion object {
    const val subreddit = "androiddev"
  }


}