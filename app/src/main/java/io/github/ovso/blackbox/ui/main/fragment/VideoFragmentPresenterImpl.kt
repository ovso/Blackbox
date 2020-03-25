package io.github.ovso.blackbox.ui.main.fragment

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.text.TextUtils
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.data.KeyName
import io.github.ovso.blackbox.data.network.SearchRequest
import io.github.ovso.blackbox.data.network.model.Search
import io.github.ovso.blackbox.data.network.model.SearchItem
import io.github.ovso.blackbox.ui.main.fragment.adapter.VideoAdapterDataModel
import io.github.ovso.blackbox.utils.ResourceProvider
import io.github.ovso.blackbox.utils.SchedulersFacade
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import timber.log.Timber

class VideoFragmentPresenterImpl(
  private val view: VideoFragmentPresenter.View,
  private val searchRequest: SearchRequest,
  private val resourceProvider: ResourceProvider,
  private val schedulersFacade: SchedulersFacade,
  private val adapterDataModel: VideoAdapterDataModel<SearchItem>
) : VideoFragmentPresenter {

  private val compositeDisposable = CompositeDisposable()
  private var nextPageToken: String? = null
  private var q: String? = null
  private var position: Int = 0

  private val title: StringBuilder
    get() {
      val builder = StringBuilder()
      builder.append(resourceProvider.getString(R.string.app_name))
      builder.append(" - ")
      builder.append(resourceProvider.getStringArray(R.array.nav_menu)[position])
      return builder
    }

  override fun onActivityCreated(args: Bundle) {
    view.setupRecyclerView()
    view.setupSwipeRefresh()
    position = args.getInt(KeyName.POSITION.get())
    view.changeTitle(title)
    q = resourceProvider.getStringArray(R.array.q)[position]
    reqVideo()
  }

  private fun reqVideo() {
    if (adapterDataModel.size > 0) return
    view.showLoading()
    searchRequest.getResult(q!!, nextPageToken)
      .subscribeOn(schedulersFacade.io())
      .observeOn(schedulersFacade.ui())
      .subscribe(object : SingleObserver<Search> {
        override fun onSuccess(t: Search) {
          nextPageToken = t.nextPageToken
          val items = t.items
          adapterDataModel.addAll(items!!)
          view.refresh()
          view.setLoaded()
          view.hideLoading()
        }

        override fun onSubscribe(d: Disposable) {
          compositeDisposable.add(d)
        }

        override fun onError(e: Throwable) {
          Timber.e(e)
          view.hideLoading()
        }

      })
  }

  override fun onDestroyView() {
    compositeDisposable.clear()
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
    if (!TextUtils.isEmpty(nextPageToken) && !TextUtils.isEmpty(q)) {
      val disposable = searchRequest.getResult(q!!, nextPageToken)
        .subscribeOn(schedulersFacade.io())
        .observeOn(schedulersFacade.ui())
        .subscribe(
          { search ->
            nextPageToken = search.nextPageToken
            val items = search.items
            adapterDataModel.addAll(items!!)
            view.refresh()
            view.setLoaded()
          }, { throwable -> Timber.d(throwable) })
      compositeDisposable.add(disposable)
    }
  }

  override fun onRefresh() {
    adapterDataModel.clear()
    view.refresh()
    nextPageToken = null
    q = resourceProvider.getStringArray(R.array.q)[position]
    val disposable = searchRequest.getResult(q!!, nextPageToken)
      .subscribeOn(schedulersFacade.io())
      .observeOn(schedulersFacade.ui())
      .subscribe(
        { search ->
          nextPageToken = search.nextPageToken
          val items = search.items
          adapterDataModel.addAll(items!!)
          view.refresh()
          view.setLoaded()
          view.hideLoading()
        }, { throwable ->
          Timber.d(throwable)
          view.hideLoading()
        })
    compositeDisposable.add(disposable)
  }
}
