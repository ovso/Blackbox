package io.github.ovso.blackbox.ui.main

import android.os.Bundle

interface MainPresenter {

  fun onCreate(savedInstanceState: Bundle?)

  interface View {

    fun setupNavigation()

  }
}
