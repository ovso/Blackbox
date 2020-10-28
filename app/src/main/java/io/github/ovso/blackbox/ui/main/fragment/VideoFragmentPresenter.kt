package io.github.ovso.blackbox.ui.main.fragment

import io.github.ovso.blackbox.data.network.model.SearchItem

interface VideoFragmentPresenter {

//  fun onActivityCreated(args: Bundle)

  fun onDestroyView()

  fun onItemClick(data: SearchItem)

  fun onLoadMore()

  fun onRefresh()

  fun onViewCreated()

  interface View {

    fun setupRecyclerView()

    fun refresh()

    fun showPortraitVideo(videoId: String)

    fun showLandscapeVideo(videoId: String)

    fun showYoutubeUseWarningDialog()

    fun setLoaded()

    fun changeTitle(title: CharSequence)
  }
}
