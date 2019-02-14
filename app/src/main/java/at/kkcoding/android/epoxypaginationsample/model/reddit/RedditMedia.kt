package at.kkcoding.android.epoxypaginationsample.model.reddit

class RedditMedia(
  val reddit_video: RedditVideo?,
  val type: String?,
  val oembed: RedditOembed?
)

class RedditVideo(
  val fallback_url: String,
  val is_gif: Boolean,
  val duration: Int
)

class RedditOembed(
  val title: String,
  val thumbnail_url: String,
  val html: String,
  val proivder_name: String,
  val provider_url: String
)