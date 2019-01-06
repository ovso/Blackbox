package io.github.ovso.blackbox.ui.base.interfaces

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class OnEndlessRecyclerScrollListener internal constructor(builder: OnEndlessRecyclerScrollListener.Builder) :
    RecyclerView.OnScrollListener() {

  private var visibleThreshold = 1
  private var lastVisibleItem: Int = 0
  private var totalItemCount: Int = 0
  private var loading: Boolean = false
  private val linearLayoutManager: LinearLayoutManager?
  private val onLoadMoreListener: OnLoadMoreListener?

  init {
    linearLayoutManager = builder.linearLayoutManager
    onLoadMoreListener = builder.onLoadMoreListener
    visibleThreshold = builder.visibleThreshold
  }

  override fun onScrolled(
    recyclerView: RecyclerView,
    dx: Int,
    dy: Int
  ) {
    super.onScrolled(recyclerView, dx, dy)

    totalItemCount = linearLayoutManager!!.itemCount
    lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
    if (!loading && totalItemCount <= lastVisibleItem + visibleThreshold) {
      loading = true
      onLoadMoreListener?.onLoadMore()
    }
  }

  fun setLoaded() {
    loading = false
  }

  interface OnLoadMoreListener {
    fun onLoadMore()
  }

  class Builder : IBuilder<OnEndlessRecyclerScrollListener> {
    var linearLayoutManager: LinearLayoutManager? = null
    var onLoadMoreListener: OnLoadMoreListener? = null
    var visibleThreshold = 1

    fun setVisibleThreshold(visibleThreshold: Int): Builder {
      this.visibleThreshold = visibleThreshold
      return this
    }

    fun setLinearLayoutManager(layoutManager: LinearLayoutManager): Builder {
      this.linearLayoutManager = layoutManager;
      return this
    }

    fun setOnLoadMoreListener(listener: OnLoadMoreListener): Builder {
      this.onLoadMoreListener = listener;
      return this
    }

    override fun build(): OnEndlessRecyclerScrollListener {
      return OnEndlessRecyclerScrollListener(this)
    }
  }
}
