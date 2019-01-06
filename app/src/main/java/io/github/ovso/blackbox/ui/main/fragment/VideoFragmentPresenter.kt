package io.github.ovso.blackbox.ui.main.fragment

import android.content.DialogInterface
import android.os.Bundle
import io.github.ovso.blackbox.data.network.model.SearchItem

interface VideoFragmentPresenter {

  fun onActivityCreated(args: Bundle)

  fun onDestroyView()

  fun onItemClick(data: SearchItem)

  fun onLoadMore()

  fun onRefresh()

  interface View {

    fun setupRecyclerView()

    fun refresh()

    fun showVideoTypeDialog(onClickListener: DialogInterface.OnClickListener)

    fun showPortraitVideo(videoId: String)

    fun showLandscapeVideo(videoId: String)

    fun showYoutubeUseWarningDialog()

    fun setLoaded()

    fun setupSwipeRefresh()

    fun hideLoading()

    fun showLoading()

    fun changeTitle(title: CharSequence)
  }
}
