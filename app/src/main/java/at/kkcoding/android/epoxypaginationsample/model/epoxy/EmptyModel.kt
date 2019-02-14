package at.kkcoding.android.epoxypaginationsample.model.epoxy

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import at.kkcoding.android.epoxypaginationsample.R
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder

@EpoxyModelClass(layout = R.layout.item_empty)
abstract class EmptyModel : EpoxyModelWithHolder<EmptyModel.ViewHolder>() {

  @EpoxyAttribute
  var text = ""

  override fun bind(holder: ViewHolder) {
    super.bind(holder)
    val emptyText = holder.itemView as TextView
    emptyText.text = text
  }

  class ViewHolder : EpoxyHolder() {
    var itemView: View? = null
    override fun bindView(itemView: View) {
      this.itemView = itemView
    }
  }
}