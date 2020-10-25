package io.github.ovso.blackbox

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.ovso.blackbox.di.DaggerAppComponent

class App : DaggerApplication() {

  override fun onCreate() {
    super.onCreate()
    Library.init(this)
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder()
      .application(this)
      .build()
  }
}
