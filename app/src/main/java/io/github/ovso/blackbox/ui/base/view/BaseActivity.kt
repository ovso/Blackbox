package io.github.ovso.blackbox.ui.base.view

import android.os.Bundle
import android.view.MenuItem
import android.view.ViewGroup
import dagger.android.support.DaggerAppCompatActivity
import io.github.ovso.blackbox.Ads
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.exts.loadAdaptiveBanner
import kotlinx.android.synthetic.main.app_bar_main.*

abstract class BaseActivity : DaggerAppCompatActivity() {

  protected abstract val layoutResID: Int

  private val isTitle: Boolean
    get() = true

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layoutResID)
//    setSupportActionBar(toolbar)
//    supportActionBar!!.setDisplayShowTitleEnabled(isTitle)
    onCreated(savedInstanceState)
    findViewById<ViewGroup>(R.id.ad_container)?.let {
      loadAdaptiveBanner(it, Ads.BANNER_UNIT_ID)
    }

  }

  protected abstract fun onCreated(savedInstanceState: Bundle?)

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    finish()
    return super.onOptionsItemSelected(item)
  }
}
