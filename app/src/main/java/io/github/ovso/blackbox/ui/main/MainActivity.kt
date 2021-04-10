package io.github.ovso.blackbox.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.InterstitialAd
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.orhanobut.logger.Logger
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.exts.loadAdaptiveBanner
import io.github.ovso.blackbox.exts.loadInterstitial
import io.github.ovso.blackbox.ui.base.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main2.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainPresenter.View {
  override val layoutResID: Int
    get() = R.layout.activity_main2

  @Inject
  lateinit var presenter: MainPresenter

  private val interstitialAd by lazy {
    loadInterstitial()
  }

  override fun onCreated(savedInstanceState: Bundle?) {
    presenter.onCreate(savedInstanceState)
    interstitialAd.adListener = object : AdListener() {
      override fun onAdLoaded() {
        super.onAdLoaded()
        interstitialAd.show()
      }
    }
  }

  override fun setupNavigation() {
    val navView: BottomNavigationView = findViewById(R.id.bnv_main)
    val navController = findNavController(R.id.nav_host_fragment)
    val appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.navigation_blackbox,
        R.id.navigation_mis_ratio,
        R.id.navigation_copi_with,
        R.id.navigation_black_box_overseas
      )
    )
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)
    navView.setOnNavigationItemReselectedListener {
      Logger.d(it.toString())
    }
    ads_container.loadAdaptiveBanner()
  }
}
