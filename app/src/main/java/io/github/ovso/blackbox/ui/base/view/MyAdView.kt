package io.github.ovso.blackbox.ui.base.view

import android.content.Context
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import io.github.ovso.blackbox.Ads

object MyAdView {

  fun getAdmobAdView(context: Context): AdView {
    val adView = AdView(context)
    adView.adSize = AdSize.SMART_BANNER
    adView.adUnitId = Ads.BANNER_UNIT_ID
    val adRequest = AdRequest.Builder()
        .build()
    adView.loadAd(adRequest)
    return adView
  }

  fun getAdmobInterstitialAd(context: Context): InterstitialAd {
    val interstitialAd = InterstitialAd(context)
    interstitialAd.adUnitId = Ads.INTERSTITIAL_UNIT_ID
    val adRequestBuilder = AdRequest.Builder()
    interstitialAd.loadAd(adRequestBuilder.build())
    return interstitialAd
  }
}
