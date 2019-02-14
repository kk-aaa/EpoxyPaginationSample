package at.kkcoding.android.epoxypaginationsample.model.epoxy

import android.text.format.DateFormat
import android.view.View
import android.widget.TextView
import at.kkcoding.android.epoxypaginationsample.R
import at.kkcoding.android.epoxypaginationsample.model.reddit.RedditChildResponse
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.item_reddit_post.view.*
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import android.net.Uri


@EpoxyModelClass(layout = R.layout.item_reddit_post)
abstract class RedditPostModel : EpoxyModelWithHolder<RedditPostModel.ViewHolder>() {

  @EpoxyAttribute
  lateinit var post: RedditChildResponse

  @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
  lateinit var onClick: (Uri) -> Unit

  override fun bind(holder: ViewHolder) {
    super.bind(holder)

    val dateFormat = SimpleDateFormat(
      DateFormat
        .getBestDateTimePattern(Locale.getDefault(), "dd MMMM yyyy HH mm"),
      Locale.getDefault()
    )

    val data = post.data
    val context = holder.tvAuthor.context
    holder.tvSubreddit.text =
      context.getString(R.string.reddit_post_subreddit, data.subreddit_name_prefixed)
    holder.tvAuthor.text =
      context.getString(R.string.reddit_post_author, data.author)
    holder.tvDate.text =
      context.getString(R.string.reddit_post_date, dateFormat.format(Date(data.created_utc * 1000)))
    holder.tvTitle.text =
      context.getString(R.string.reddit_post_title, data.title)

    holder.parent.setOnClickListener {
      onClick(Uri.parse("http://www.reddit.com/${data.permalink}"))
    }
  }


  inner class ViewHolder : EpoxyHolder() {
    lateinit var parent: View
    lateinit var tvSubreddit: TextView
    lateinit var tvAuthor: TextView
    lateinit var tvTitle: TextView
    lateinit var tvDate: TextView

    override fun bindView(itemView: View) {
      parent = itemView
      tvSubreddit = itemView.itemPostSubreddit
      tvAuthor = itemView.itemPostAuthor
      tvTitle = itemView.itemPostTitle
      tvDate = itemView.itemPostDate
    }

  }
}