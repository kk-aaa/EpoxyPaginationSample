package at.kkcoding.android.epoxypaginationsample.model.epoxy

import android.view.View
import at.kkcoding.android.epoxypaginationsample.R
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.item_loading)
abstract class LoadingModel : EpoxyModelWithHolder<LoadingModel.LoadingModelViewHolder>() {

  override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int) = totalSpanCount

  class LoadingModelViewHolder : EpoxyHolder() {
    override fun bindView(itemView: View) {
      // nothing to bind, just display the layout
    }
  }
}