package io.github.ovso.blackbox.ui.main.fragment.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.blackbox.data.network.model.SearchItem
import io.github.ovso.blackbox.ui.base.interfaces.OnRecyclerViewItemClickListener
import java.util.ArrayList

class VideoAdapter : RecyclerView.Adapter<VideoViewHolder>(),
  VideoAdapterView,
  VideoAdapterDataModel<SearchItem> {
  var onRecyclerViewItemClickListener: OnRecyclerViewItemClickListener<SearchItem>? = null
  private val items = ArrayList<SearchItem>()

  override val size: Int
    get() = items.size

  override fun onCreateViewHolder(
    viewGroup: ViewGroup,
    viewType: Int
  ): VideoViewHolder {
    return VideoViewHolder.create(viewGroup)
  }

  override fun onBindViewHolder(
    viewHolder: VideoViewHolder,
    position: Int
  ) {
    viewHolder.bind(getItem(position))
    viewHolder.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener
  }

  override fun getItemCount(): Int {
    return size
  }

  override fun addAll(items: List<SearchItem>) {
    this.items.addAll(items)
  }

  override fun getItem(position: Int): SearchItem {
    return items[position]
  }

  override fun clear() {
    items.clear()
  }

  override fun refresh() {
    notifyDataSetChanged()
  }

  override fun refresh(
    positionStart: Int,
    itemCount: Int
  ) {
    notifyItemRangeInserted(positionStart, itemCount)
  }
}
