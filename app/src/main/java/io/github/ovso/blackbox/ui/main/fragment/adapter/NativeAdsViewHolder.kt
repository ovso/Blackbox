package io.github.ovso.blackbox.ui.main.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import io.github.ovso.blackbox.Ads
import io.github.ovso.blackbox.data.network.model.SearchItem
import io.github.ovso.commons.databinding.DialogNativeAds2Binding

class NativeAdsViewHolder private constructor(private val binding: DialogNativeAds2Binding) :
  RecyclerView.ViewHolder(
    binding.root
  ), IBind<SearchItem> {

  override fun bind(data: SearchItem) {
    binding.progressBar.isVisible = true
    val templateView = binding.templateView
    val builder = AdLoader.Builder(itemView.context, Ads.NATIVE_UNIT_ID).apply {
      forUnifiedNativeAd {
        templateView.setNativeAd(it)
        binding.progressBar.isVisible = false
      }
    }
    val adLoader = builder.build()
    val adRequest = AdRequest.Builder().build()
    adLoader.loadAd(adRequest)
  }

  companion object {

    fun create(parent: ViewGroup): NativeAdsViewHolder {
      return NativeAdsViewHolder(
        DialogNativeAds2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
      )
    }
  }
}
