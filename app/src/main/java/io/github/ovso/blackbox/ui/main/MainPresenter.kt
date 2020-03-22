package io.github.ovso.blackbox.ui.main

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import io.github.ovso.blackbox.data.NavMenu

interface MainPresenter {

  fun onCreate(savedInstanceState: Bundle?)

  fun onNavItemSelected(@IdRes itemId: Int): Boolean

  fun onBackPressed(isDrawerOpen: Boolean)

  fun onBottomNavItemSelected(@IdRes itemId: Int, isChecked: Boolean): Boolean

  interface View {

    fun setListener()

    fun closeDrawer()

    fun finish()

    fun showBlackBox(menu: NavMenu)

    fun showMisRatio(menu: NavMenu)

    fun showCopiWith(menu: NavMenu)

    fun showMessage(@StringRes resId: Int)

    fun showMessage(msg: String)

    fun changeTheme()

    fun removeBottomNavMenu()

    fun showOverSeas(menu: NavMenu)
  }
}
