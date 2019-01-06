package io.github.ovso.blackbox.ui.main.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.data.network.model.SearchItem
import io.github.ovso.blackbox.main.base.view.BaseFragment
import io.github.ovso.blackbox.ui.base.interfaces.OnEndlessRecyclerScrollListener
import io.github.ovso.blackbox.ui.base.interfaces.OnRecyclerViewItemClickListener
import io.github.ovso.blackbox.ui.main.fragment.adapter.VideoAdapter
import io.github.ovso.blackbox.ui.main.fragment.adapter.VideoAdapterView
import io.github.ovso.blackbox.ui.video.LandscapeVideoActivity
import io.github.ovso.blackbox.ui.video.PortraitVideoActivity
import kotlinx.android.synthetic.main.fragment_video.recycler_view
import kotlinx.android.synthetic.main.fragment_video.swipe_refresh_layout
import javax.inject.Inject

class VideoFragment : BaseFragment(),
    VideoFragmentPresenter.View,
    OnRecyclerViewItemClickListener<SearchItem>,
    OnEndlessRecyclerScrollListener.OnLoadMoreListener {
  override fun onActivityCreate(savedInstanceState: Bundle?) {
    presenter!!.onActivityCreated(arguments!!)
  }

  override val layoutResID: Int
    get() = R.layout.fragment_video

  var presenter: VideoFragmentPresenter? = null
    @Inject set
  var adapter: VideoAdapter? = null
    @Inject set
  var adapterView: VideoAdapterView? = null
    @Inject set

  override fun onDestroyView() {
    presenter!!.onDestroyView()
    super.onDestroyView()
  }

  override fun setupRecyclerView() {
    recycler_view.layoutManager = LinearLayoutManager(context)
    recycler_view.setOnItemClickListener(this)
    recycler_view.addOnScrollListener(
        OnEndlessRecyclerScrollListener.Builder()
            .setVisibleThreshold(20)
            .setLinearLayoutManager(recycler_view.layoutManager as LinearLayoutManager)
            .setOnLoadMoreListener(this)
            .build()
    )
    recycler_view.adapter = adapter
  }

  override fun refresh() {
    adapterView!!.refresh()
  }

  override fun showVideoTypeDialog(onClickListener: DialogInterface.OnClickListener) {
    AlertDialog.Builder(context)
        .setMessage(R.string.please_select_the_player_mode)
        .setPositiveButton(
            R.string.portrait_mode,
            onClickListener
        )
        .setNeutralButton(R.string.landscape_mode, onClickListener)
        .setNegativeButton(android.R.string.cancel, onClickListener)
        .show()
  }

  override fun showPortraitVideo(videoId: String) {
    val intent = Intent(context, PortraitVideoActivity::class.java)
    intent.putExtra("video_id", videoId)
    startActivity(intent)
  }

  override fun showLandscapeVideo(videoId: String) {
    val intent = Intent(context, LandscapeVideoActivity::class.java)
    intent.putExtra("video_id", videoId)
    startActivity(intent)
  }

  override fun showYoutubeUseWarningDialog() {
    AlertDialog.Builder(activity)
        .setMessage(R.string.youtube_use_warning)
        .setPositiveButton(android.R.string.ok, null)
        .show()
  }

  override fun setLoaded() {
    recycler_view.onEndlessRecyclerScrollListener!!.setLoaded()
  }

  override fun setupSwipeRefresh() {
    swipe_refresh_layout.setOnRefreshListener { presenter!!.onRefresh() }
  }

  override fun hideLoading() {
    swipe_refresh_layout.isRefreshing = false
  }

  override fun showLoading() {
    swipe_refresh_layout.isRefreshing = true
  }

  override fun changeTitle(title: CharSequence) {
    activity!!.title = title
  }

  override fun onItemClick(
    view: View,
    data: SearchItem,
    itemPosition: Int
  ) {
    presenter!!.onItemClick(data)
  }

  override fun onLoadMore() {
    presenter!!.onLoadMore()
  }

  companion object {

    fun newInstance(args: Bundle): Fragment {
      val f = VideoFragment()
      f.arguments = args
      return f
    }
  }
}