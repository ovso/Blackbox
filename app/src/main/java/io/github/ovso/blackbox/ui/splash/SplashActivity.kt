package io.github.ovso.blackbox.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.LoadAdError
import io.github.ovso.blackbox.exts.loadInterstitial
import io.github.ovso.blackbox.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
  private val interstitialAd by lazy { loadInterstitial() }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    interstitialAd.adListener = object : AdListener() {

      override fun onAdFailedToLoad(p0: LoadAdError?) {
        super.onAdFailedToLoad(p0)
        launchMain()
      }

      override fun onAdLoaded() {
        super.onAdLoaded()
        interstitialAd.show()
      }

      override fun onAdClosed() {
        super.onAdClosed()
        launchMain()
      }
    }
  }

  private fun launchMain() {
    Intent(this, MainActivity::class.java).apply {
      startActivity(this)
      finish()
    }
  }
}
