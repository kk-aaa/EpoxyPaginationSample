package at.kkcoding.android.epoxypaginationsample.model.reddit

class RedditChildData(
  val subreddit: String,
  val subreddit_name_prefixed: String,
  val author: String,
  val title: String,
  val selftext: String,
  val selftext_html: String,
  val created_utc: Long,
  val permalink: String,
  val url: String,
  val num_comments: Int,
  val ups: Int,
  val downs: Int,
  val is_video: Boolean,
  val media: RedditMedia?
)