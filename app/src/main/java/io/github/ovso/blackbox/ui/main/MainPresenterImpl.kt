package io.github.ovso.blackbox.ui.main

import android.os.Bundle
import com.orhanobut.logger.Logger

class MainPresenterImpl internal constructor(
  private val view: MainPresenter.View,
  private val language: String
) : MainPresenter {

  override fun onCreate(savedInstanceState: Bundle?) {
    Logger.d(language)
    view.setupNavigation()
  }
}
