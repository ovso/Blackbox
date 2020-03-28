package io.github.ovso.blackbox.ui.main.fragment.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.github.ovso.blackbox.Ads
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.data.network.model.SearchItem
import io.github.ovso.blackbox.exts.loadAdaptiveBanner
import kotlinx.android.extensions.LayoutContainer

class AdsViewHolder private constructor(override val containerView: View?) :
  RecyclerView.ViewHolder(
    containerView!!
  ), IBind<SearchItem>, LayoutContainer {

  override fun bind(data: SearchItem) {
    (itemView.context as? Activity)?.loadAdaptiveBanner((itemView as ViewGroup), Ads.BANNER_UNIT_ID)
  }

  companion object {

    fun create(parent: ViewGroup): AdsViewHolder {
      return AdsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_ads, parent, false)
      )
    }
  }
}
