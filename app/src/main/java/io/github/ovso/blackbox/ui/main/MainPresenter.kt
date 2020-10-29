package io.github.ovso.blackbox.ui.main

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.StringRes

interface MainPresenter {

  fun onCreate(savedInstanceState: Bundle?)

  fun onNavItemSelected(@IdRes itemId: Int): Boolean

  interface View {

    fun closeDrawer()

    fun showMessage(@StringRes resId: Int)

    fun showMessage(msg: String)
  }
}
