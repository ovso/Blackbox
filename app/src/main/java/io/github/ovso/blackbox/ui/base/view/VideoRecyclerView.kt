package io.github.ovso.blackbox.ui.base.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import io.github.ovso.blackbox.data.network.model.SearchItem
import io.github.ovso.blackbox.ui.base.interfaces.OnEndlessRecyclerScrollListener
import io.github.ovso.blackbox.ui.base.interfaces.OnRecyclerViewItemClickListener
import io.github.ovso.blackbox.ui.main.fragment.adapter.VideoAdapter
import io.github.ovso.blackbox.utils.ObjectUtils

class VideoRecyclerView : RecyclerView {
  private var onRecyclerViewItemClickListener: OnRecyclerViewItemClickListener<SearchItem>? = null
  var onEndlessRecyclerScrollListener: OnEndlessRecyclerScrollListener? = null

  constructor(context: Context) : super(context) {}

  constructor(
    context: Context,
    attrs: AttributeSet?
  ) : super(context, attrs) {
  }

  constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyle: Int
  ) : super(context, attrs, defStyle) {
  }

  override fun setAdapter(adapter: RecyclerView.Adapter<*>?) {
    super.setAdapter(adapter)
    setOnItemClickListener(adapter)
  }

  fun setOnItemClickListener(listener: OnRecyclerViewItemClickListener<SearchItem>) {
    onRecyclerViewItemClickListener = listener
    setOnItemClickListener(adapter)
  }

  private fun setOnItemClickListener(adapter: RecyclerView.Adapter<*>?) {
    if (!ObjectUtils.isEmpty(adapter)) {
      val videoAdapter = adapter as VideoAdapter?
      videoAdapter?.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener
    }
  }

  override fun addOnScrollListener(listener: RecyclerView.OnScrollListener) {
    super.addOnScrollListener(listener)
    onEndlessRecyclerScrollListener = listener as OnEndlessRecyclerScrollListener
  }
}
