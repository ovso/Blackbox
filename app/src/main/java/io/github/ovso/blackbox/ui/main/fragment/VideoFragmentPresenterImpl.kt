package io.github.ovso.blackbox.ui.main.fragment

import android.content.ActivityNotFoundException
import androidx.activity.OnBackPressedCallback
import com.orhanobut.logger.Logger
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.data.args.VideoArgs
import io.github.ovso.blackbox.data.network.SearchRequest
import io.github.ovso.blackbox.data.network.getAdsAddedItem
import io.github.ovso.blackbox.data.network.model.Search
import io.github.ovso.blackbox.data.network.model.SearchItem
import io.github.ovso.blackbox.ui.main.fragment.adapter.VideoAdapterDataModel
import io.github.ovso.blackbox.utils.SchedulersFacade
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import timber.log.Timber

class VideoFragmentPresenterImpl(
  private val view: VideoFragmentPresenter.View,
  private val searchRequest: SearchRequest,
  private val schedulersFacade: SchedulersFacade,
  private val adapterDataModel: VideoAdapterDataModel<SearchItem>,
  private val args: VideoArgs
) : VideoFragmentPresenter {

  private val compositeDisposable = CompositeDisposable()
  private var nextPageToken: String? = null

  private val onBackPressedCallback = object : OnBackPressedCallback(true) {
    override fun handleOnBackPressed() {
      view.showNativeAdsDialog()
    }
  }

  override fun onViewCreated() {
    view.setupRecyclerView()
    reqVideo(args.query)
  }

  override fun onResume() {
    if (args.resId == R.string.query_blackbox_ko) {
      view.addOnBackPressedDispatcher(onBackPressedCallback) // 모든 탭에 동일한 Fragment를 사용하기 때문에 조건에 따른 처리가 필요함.
    }
  }

  override fun onPause() {
    onBackPressedCallback.remove()
  }


  private fun reqVideo(query: String) {
    fun onSuccess(data: Search) {
      Logger.d(data.items?.count())
      nextPageToken = data.nextPageToken
      adapterDataModel.addAll(searchRequest.getAdsAddedItem(data.items!!))
      view.refresh()
    }

    fun onFailure(throwable: Throwable) {
      Logger.e(throwable, throwable.message.toString())
    }

    searchRequest.getResult(query, nextPageToken)
      .subscribeOn(schedulersFacade.io())
      .observeOn(schedulersFacade.ui())
      .doOnSubscribe { view.showLoading() }
      .doOnSuccess { view.hideLoading() }
      .doOnError { view.hideLoading() }
      .subscribe(::onSuccess, ::onFailure)
      .addTo(compositeDisposable)
  }

  override fun onDestroyView() {
    compositeDisposable.clear()
    onBackPressedCallback.remove()
  }

  override fun onItemClick(data: SearchItem) {
    val videoId = data.id!!.videoId
    try {
      view.showPortraitVideo(videoId!!)
    } catch (e: ActivityNotFoundException) {
      Timber.e(e)
      view.showYoutubeUseWarningDialog()
    }
  }

  override fun onLoadMore() {
  }

  override fun onRefresh() {
  }

}
