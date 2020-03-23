package io.github.ovso.blackbox

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.ovso.blackbox.di.DaggerAppComponent
import io.github.ovso.blackbox.utils.AppInitUtils

class App : DaggerApplication() {

  override fun onCreate() {
    super.onCreate()
    AppInitUtils.timer()
    AppInitUtils.ads(this)
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder()
      .application(this)
      .build()
  }
}
