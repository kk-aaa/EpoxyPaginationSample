package at.kkcoding.android.epoxypaginationsample.features.feed

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import at.kkcoding.android.epoxypaginationsample.R
import at.kkcoding.android.epoxypaginationsample.classes.BaseActivity
import kotlinx.android.synthetic.main.activity_feed.*
import javax.inject.Inject

class FeedActivity : BaseActivity() {

  @Inject
  lateinit var controller: FeedController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_feed)
    setupRecyclerView()
  }

  private fun setupRecyclerView() {
    rvFeed.apply {
      layoutManager = LinearLayoutManager(this@FeedActivity)
      itemAnimator = DefaultItemAnimator()
      adapter = controller.adapter
    }
  }

  override fun onStart() {
    super.onStart()
    viewModel(FeedViewModel::class.java).feed
      .observe(this, Observer { pagedlist ->
        controller.submitList(pagedlist)
      })
  }

  fun openLink(uri: Uri) {
    startActivity(Intent(Intent.ACTION_VIEW, uri))
  }
}
