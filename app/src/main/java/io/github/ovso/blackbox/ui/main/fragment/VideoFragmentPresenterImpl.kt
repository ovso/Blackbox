package io.github.ovso.blackbox.ui.main.fragment

import android.content.ActivityNotFoundException
import android.content.DialogInterface.OnClickListener
import android.os.Bundle
import android.text.TextUtils
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.data.KeyName
import io.github.ovso.blackbox.data.VideoMode
import io.github.ovso.blackbox.data.network.SearchRequest
import io.github.ovso.blackbox.data.network.model.SearchItem
import io.github.ovso.blackbox.ui.main.fragment.adapter.VideoAdapterDataModel
import io.github.ovso.blackbox.utils.ResourceProvider
import io.github.ovso.blackbox.utils.SchedulersFacade
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class VideoFragmentPresenterImpl(
  private val view: VideoFragmentPresenter.View,
  private val searchRequest: SearchRequest,
  private val resourceProvider: ResourceProvider,
  private val schedulersFacade: SchedulersFacade,
  private val adapterDataModel: VideoAdapterDataModel<SearchItem>
) : io.github.ovso.blackbox.ui.main.fragment.VideoFragmentPresenter {

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
    view.showLoading()
    position = args.getInt(KeyName.POSITION.get())
    view.changeTitle(title)
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
            }, { throwable -> view.hideLoading() })
    compositeDisposable.add(disposable)
  }

  override fun onDestroyView() {
    compositeDisposable.clear()
  }

  override fun onItemClick(data: SearchItem) {
    val onClickListener = OnClickListener { dialog, which ->
      dialog.dismiss()

      try {
        dialog.dismiss()
        val videoId = data.id!!.videoId
        when (VideoMode.toMode(which)) {
          VideoMode.PORTRAIT -> view.showPortraitVideo(videoId!!)
          VideoMode.LANDSCAPE -> view.showLandscapeVideo(videoId!!)
          VideoMode.CANCEL -> {
          }
        }
      } catch (e: ActivityNotFoundException) {
        e.printStackTrace()
        view.showYoutubeUseWarningDialog()
      }
    }
    view.showVideoTypeDialog(onClickListener)
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

  //private <E> void shuffle(List<E> $items) {
  //  Collections.shuffle($items);
  //}
}