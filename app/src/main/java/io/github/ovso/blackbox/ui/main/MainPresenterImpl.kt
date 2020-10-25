package io.github.ovso.blackbox.ui.main

import android.os.Bundle

class MainPresenterImpl internal constructor(
  private val view: MainPresenter.View,
  private val language: String
) : MainPresenter {

  override fun onCreate(savedInstanceState: Bundle?) {
    view.setListener()

    setupBottomNav()
  }

  private fun setupBottomNav() {
    if (!language.equals("ko", ignoreCase = true)) {
      view.removeBottomNavMenu()
    }
  }

  override fun onNavItemSelected(itemId: Int): Boolean {
    view.closeDrawer()
    return true
  }

  override fun onBackPressed(isDrawerOpen: Boolean) {
    when (isDrawerOpen) {
      true -> view.closeDrawer()
      else -> view.showNativeAdsDialog()
    }
  }
}
