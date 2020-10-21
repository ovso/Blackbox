package io.github.ovso.blackbox.ui.main

import android.os.Bundle
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainPresenterImpl internal constructor(
  private val view: MainPresenter.View,
  private val language: String
) : MainPresenter {
  private val compositeDisposable: CompositeDisposable = CompositeDisposable()

  init {
    view.changeTheme()
  }

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
    if (isDrawerOpen) {
      view.closeDrawer()
    } else {
      compositeDisposable.clear()
      view.finish()
    }
  }
}
