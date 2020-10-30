package io.github.ovso.blackbox.ui.main.fragment.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import io.github.ovso.blackbox.Ads
import io.github.ovso.blackbox.data.network.model.SearchItem
import io.github.ovso.commons.databinding.ViewFeedAdsSmallBinding


class NativeAdsViewHolder private constructor(private val binding: ViewFeedAdsSmallBinding) :
  RecyclerView.ViewHolder(
    binding.root
  ), IBind<SearchItem> {

  override fun bind(data: SearchItem) {
    binding.progressBar.isVisible = true
    val templateView = binding.templateView
    val adLoader = AdLoader.Builder(itemView.context, Ads.NATIVE_UNIT_ID).apply {
      forUnifiedNativeAd {
        val styles = NativeTemplateStyle.Builder()
          .withCallToActionTextTypeface(Typeface.DEFAULT_BOLD)
          .build()
        templateView.setStyles(styles)
        templateView.setNativeAd(it)
        binding.progressBar.isVisible = false
      }
    }.build()
    adLoader.loadAd(AdRequest.Builder().build())
  }

  companion object {

    fun create(parent: ViewGroup): NativeAdsViewHolder {
      return NativeAdsViewHolder(
        ViewFeedAdsSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
      )
    }
  }
}
