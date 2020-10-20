package io.github.ovso.blackbox.ui.base.view

import android.os.Bundle
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import io.github.ovso.blackbox.Ads
import io.github.ovso.blackbox.exts.loadAdaptiveBanner
import kotlinx.android.synthetic.main.app_bar_main.*

abstract class BaseActivity : DaggerAppCompatActivity() {

  protected abstract val layoutResID: Int

  val isTitle: Boolean
    get() = true

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layoutResID)
    setSupportActionBar(toolbar)
    supportActionBar!!.setDisplayShowTitleEnabled(isTitle)
    onCreated(savedInstanceState)
    loadAdaptiveBanner(ad_container, Ads.BANNER_UNIT_ID)
  }

  protected abstract fun onCreated(savedInstanceState: Bundle?)

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    finish()
    return super.onOptionsItemSelected(item)
  }
}
