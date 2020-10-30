package io.github.ovso.blackbox.ui.main.fragment.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.blackbox.data.network.model.SearchItem
import io.github.ovso.blackbox.ui.base.interfaces.OnRecyclerViewItemClickListener
import java.util.ArrayList

const val ITEM_TYPE_NORMAL = 0
const val ITEM_TYPE_ADS = 1

class VideoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(),
  VideoAdapterView,
  VideoAdapterDataModel<SearchItem> {
  var onRecyclerViewItemClickListener: OnRecyclerViewItemClickListener<SearchItem>? = null
  private val items = ArrayList<SearchItem>()

  override val size: Int
    get() = items.size

  override fun onCreateViewHolder(
    viewGroup: ViewGroup,
    viewType: Int
  ): RecyclerView.ViewHolder {
    return when (viewType == ITEM_TYPE_NORMAL) {
      true -> VideoViewHolder.create(viewGroup)
      false -> BannerAdsViewHolder.create(viewGroup)
    }
  }

  override fun getItemViewType(position: Int): Int {
    return getItem(position).snippet?.let { ITEM_TYPE_NORMAL } ?: ITEM_TYPE_ADS
  }

  override fun onBindViewHolder(
    viewHolder: RecyclerView.ViewHolder,
    position: Int
  ) {
    if (viewHolder is VideoViewHolder) {
      viewHolder.bind(getItem(position))
      viewHolder.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener
    } else if (viewHolder is BannerAdsViewHolder) {
      viewHolder.bind(getItem(position))
    }
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
