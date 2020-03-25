package io.github.ovso.blackbox.ui.base.view

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import com.google.android.gms.ads.AdListener
import io.github.ovso.blackbox.exts.loadInterstitial

@SuppressLint("Registered")
open class AdsActivity : Activity() {

  private val interstitialAd by lazy {
    loadInterstitial()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    interstitialAd.adListener = object : AdListener() {
      override fun onAdClosed() {
        super.onAdClosed()
        finish()
      }
    }
  }

  override fun onBackPressed() {
    if (interstitialAd.isLoaded) {
      interstitialAd.show()
    } else {
      finish()
    }
  }
}
