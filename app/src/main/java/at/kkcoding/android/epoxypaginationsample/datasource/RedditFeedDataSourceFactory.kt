package at.kkcoding.android.epoxypaginationsample.datasource

import androidx.paging.DataSource
import at.kkcoding.android.epoxypaginationsample.api.RedditApi
import at.kkcoding.android.epoxypaginationsample.model.reddit.RedditChildResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RedditFeedDataSourceFactory @Inject constructor(private val redditApi: RedditApi) :
  DataSource.Factory<String, RedditChildResponse>() {

  override fun create(): DataSource<String, RedditChildResponse> {
    return PageKeyedFeedDataSource(redditApi)
  }
}