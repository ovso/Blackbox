package io.github.ovso.blackbox.ui.main.fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
), IBind<SearchItem>, LayoutContainer {

  private var data: SearchItem? = null
  var onRecyclerViewItemClickListener: OnRecyclerViewItemClickListener<SearchItem>? = null

  override fun bind(data: SearchItem) {
    this.data = data
    Glide.with(title_text_view.context)
        .load(getImageUrl())
        .into(thumbnail_image_view)

    title_text_view.text = data.snippet?.title

    val language = Locale.getDefault()
        .language
    val format: String = getDateFormat(language)
    date_text_view.text = DateUtils.getDate(data.snippet?.publishedAt!!, format)
    play_button.setOnClickListener {
      onRecyclerViewItemClickListener?.onItemClick(
          itemView, this.data!!, 0
      )
    }
  }

  private fun getDateFormat(language: String): String {
    if (language.equals("ko", ignoreCase = true)) {
      return "yyyy년 MM월 dd일 HH시 mm분"
    } else {
      return "yyyy/MM/dd HH:mm"
    }
  }

  private fun getImageUrl(): Any? {
    return data?.snippet?.thumbnails?.medium?.url
  }

  companion object {

    fun create(parent: ViewGroup): VideoViewHolder {
      return VideoViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.list_item_video, parent, false)
      )
    }
  }
}
