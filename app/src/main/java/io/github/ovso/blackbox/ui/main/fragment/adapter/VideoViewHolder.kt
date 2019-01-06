package io.github.ovso.blackbox.ui.main.fragment.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.data.network.model.SearchItem
import io.github.ovso.blackbox.ui.base.interfaces.OnRecyclerViewItemClickListener
import io.github.ovso.blackbox.utils.DateUtils
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_video.date_text_view
import kotlinx.android.synthetic.main.list_item_video.play_button
import kotlinx.android.synthetic.main.list_item_video.thumbnail_image_view
import kotlinx.android.synthetic.main.list_item_video.title_text_view
import java.util.Locale

class VideoViewHolder private constructor(override val containerView: View?) : RecyclerView.ViewHolder(
    containerView!!
), Bindable<SearchItem>, LayoutContainer {

  private var data: SearchItem? = null
  var onRecyclerViewItemClickListener: OnRecyclerViewItemClickListener<SearchItem>? = null

  override fun bind(item: SearchItem) {
    data = item
    Glide.with(title_text_view.context)
        .load(item.snippet?.thumbnails?.medium?.url)
        .into(thumbnail_image_view)
    title_text_view.text = item.snippet?.title

    val language = Locale.getDefault()
        .language
    val format: String
    if (language.equals("ko", ignoreCase = true)) {
      format = "yyyy년 MM월 dd일 HH시 mm분"
    } else {
      format = "yyyy/MM/dd HH:mm"
    }

    date_text_view.text = DateUtils.getDate(item.snippet?.publishedAt!!, format)
    play_button.setOnClickListener { onRecyclerViewItemClickListener?.onItemClick(itemView, data!!, 0) }
  }

  companion object {

    fun create(parent: ViewGroup): VideoViewHolder {
      return VideoViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.list_item_video, parent, false)
      )
    }
  }
}
