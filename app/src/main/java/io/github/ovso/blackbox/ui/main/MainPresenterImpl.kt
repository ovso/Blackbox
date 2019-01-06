package io.github.ovso.blackbox.ui.main

import android.os.Bundle
import android.support.annotation.IdRes
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.data.NavMenu
import io.reactivex.disposables.CompositeDisposable

class MainPresenterImpl internal constructor(
  private val view: MainPresenter.View,
  private val language: String
) : MainPresenter {
  private val compositeDisposable: CompositeDisposable

  init {
    this.compositeDisposable = CompositeDisposable()
    view.changeTheme()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    view.setListener()

    setupBottomNav()
    view.showBlackBox(NavMenu.BLACK_BOX)
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

  override fun onBottomNavItemSelected(@IdRes itemId: Int, isChecked: Boolean): Boolean {
    if (!isChecked) {
      when (itemId) {
        R.id.action_black_box -> view.showBlackBox(NavMenu.BLACK_BOX)
        R.id.action_mis_ratio -> view.showMisRatio(NavMenu.MIS_RATIO)
        R.id.action_copi_with -> view.showCopiWith(NavMenu.COPI_WITH)
        R.id.action_oversease_black_box -> {
          view.showCopiWith(NavMenu.COPI_WITH)
          view.showOverSeas(NavMenu.OVER_SEAS)
        }
      }
    }
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
