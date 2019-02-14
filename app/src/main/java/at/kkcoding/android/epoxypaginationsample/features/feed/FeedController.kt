package at.kkcoding.android.epoxypaginationsample.features.feed

import at.kkcoding.android.epoxypaginationsample.application.di.helpers.PerActivity
import at.kkcoding.android.epoxypaginationsample.model.epoxy.EmptyModel_
import at.kkcoding.android.epoxypaginationsample.model.epoxy.LoadingModel_
import at.kkcoding.android.epoxypaginationsample.model.epoxy.RedditPostModel_
import at.kkcoding.android.epoxypaginationsample.model.reddit.RedditChildResponse
import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import javax.inject.Inject

@PerActivity
class FeedController @Inject constructor(private val view: FeedActivity) :
  PagedListEpoxyController<RedditChildResponse>(modelBuildingHandler = EpoxyAsyncUtil.getAsyncBackgroundHandler()) {

  var endReached = false

  override fun buildItemModel(currentPosition: Int, item: RedditChildResponse?): EpoxyModel<*> {
    return if (item == null)
      EmptyModel_().id(currentPosition)
    else
      RedditPostModel_()
        .post(item)
        .onClick { view.openLink(it) }
        .id(item.data.permalink)
  }

  override fun addModels(models: List<EpoxyModel<*>>) {
    super.addModels(models)

    LoadingModel_()
      .id("loading")
      .addIf(!endReached && models.isNotEmpty(), this)

  }
}