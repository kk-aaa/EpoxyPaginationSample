package at.kkcoding.android.epoxypaginationsample.api

import at.kkcoding.android.epoxypaginationsample.model.reddit.RedditJsonResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RedditApi {

  @GET("{subreddit}/top.json")
  fun fetchTop(
    @Path("subreddit") subreddit: String,
    @Query("limit") limit: Int = 20,
    @Query("after") after: String = ""
  ): Single<RedditJsonResponse>

  @GET("{subreddit}/new.json")
  fun fetchNew(
    @Path("subreddit") subreddit: String,
    @Query("limit") limit: Int = 20,
    @Query("after") after: String = ""
  ): Single<RedditJsonResponse>

}