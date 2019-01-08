package io.github.ovso.blackbox.ui.base.view

import android.os.Bundle
import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.app_bar_main.ad_container
import kotlinx.android.synthetic.main.app_bar_main.toolbar

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
    ad_container.addView(MyAdView.getAdmobAdView(this))
  }

  protected abstract fun onCreated(savedInstanceState: Bundle?)

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    finish()
    return super.onOptionsItemSelected(item)
  }
}