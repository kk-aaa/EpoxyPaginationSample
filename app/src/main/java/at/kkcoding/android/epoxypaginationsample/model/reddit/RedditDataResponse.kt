package at.kkcoding.android.epoxypaginationsample.model.reddit

class RedditDataResponse(
  val children: List<RedditChildResponse> = emptyList(),
  val after: String?,
  val before: String?
)