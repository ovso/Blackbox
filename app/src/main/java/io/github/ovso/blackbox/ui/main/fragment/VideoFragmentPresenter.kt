package io.github.ovso.blackbox.ui.main.fragment

import androidx.activity.OnBackPressedCallback
import io.github.ovso.blackbox.data.network.model.SearchItem

interface VideoFragmentPresenter {
  fun onDestroyView()
  fun onItemClick(data: SearchItem)
  fun onLoadMore()
  fun onRefresh()
  fun onViewCreated()
  fun onResume()
  fun onPause()

  interface View {
    fun setupRecyclerView()
    fun refresh()
    fun showPortraitVideo(videoId: String)
    fun showLandscapeVideo(videoId: String)
    fun showYoutubeUseWarningDialog()
    fun setLoaded()
    fun changeTitle(title: CharSequence)
    fun showLoading()
    fun hideLoading()
    fun addOnBackPressedDispatcher(onBackPressedCallback: OnBackPressedCallback)
    fun showNativeAdsDialog()
  }
}
