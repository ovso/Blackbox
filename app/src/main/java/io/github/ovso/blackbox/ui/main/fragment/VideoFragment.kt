package io.github.ovso.blackbox.ui.main.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.data.network.model.SearchItem
import io.github.ovso.blackbox.ui.base.interfaces.OnEndlessRecyclerScrollListener
import io.github.ovso.blackbox.ui.base.interfaces.OnRecyclerViewItemClickListener
import io.github.ovso.blackbox.ui.base.view.BaseFragment
import io.github.ovso.blackbox.ui.main.fragment.adapter.VideoAdapter
import io.github.ovso.blackbox.ui.main.fragment.adapter.VideoAdapterView
import io.github.ovso.blackbox.ui.video.LandscapeVideoActivity
import io.github.ovso.blackbox.ui.video.PortraitVideoActivity
import kotlinx.android.synthetic.main.fragment_video.*
import javax.inject.Inject

class VideoFragment : BaseFragment(),
  VideoFragmentPresenter.View,
  OnRecyclerViewItemClickListener<SearchItem>,
  OnEndlessRecyclerScrollListener.OnLoadMoreListener {

  override val layoutResID: Int
    get() = R.layout.fragment_video

  @Inject
  lateinit var presenter: VideoFragmentPresenter

  @Inject
  lateinit var adapter: VideoAdapter

  @Inject
  lateinit var adapterView: VideoAdapterView

  private val args: VideoFragmentArgs by navArgs()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    Logger.d("args = $args ${args.query}")
    presenter.onViewCreated()
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
    adapterView.refresh()
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

  override fun changeTitle(title: CharSequence) {
    requireActivity().title = title
  }

  override fun showLoading() {
    progressBar.isVisible = true
  }

  override fun hideLoading() {
    progressBar.isVisible = false
  }

  override fun onItemClick(
    view: View,
    data: SearchItem,
    itemPosition: Int
  ) {
    presenter.onItemClick(data)
  }

  override fun onLoadMore() {
//    presenter!!.onLoadMore()
  }

  companion object {
    fun newInstance(position: Int): Fragment {
      val f = VideoFragment()
      f.arguments = bundleOf("position" to position)
      return f
    }
  }


  override fun onDestroyView() {
    super.onDestroyView()
    presenter.onDestroyView()
    Logger.d("onDestroyView")
  }

  override fun onDestroy() {
    super.onDestroy()
    Logger.d("onDestroy")
  }

  override fun onDetach() {
    super.onDetach()
    Logger.d("onDetach")
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    Logger.d("onAttach")
  }

}
