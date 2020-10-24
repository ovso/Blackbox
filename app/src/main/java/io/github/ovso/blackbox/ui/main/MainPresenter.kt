package io.github.ovso.blackbox.ui.main

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.StringRes

interface MainPresenter {

  fun onCreate(savedInstanceState: Bundle?)

  fun onNavItemSelected(@IdRes itemId: Int): Boolean

  fun onBackPressed(isDrawerOpen: Boolean)

  interface View {

    fun setListener()

    fun closeDrawer()

    fun finish()

    fun showMessage(@StringRes resId: Int)

    fun showMessage(msg: String)

    fun removeBottomNavMenu()
    fun showNativeAdsDialog()
  }
}
