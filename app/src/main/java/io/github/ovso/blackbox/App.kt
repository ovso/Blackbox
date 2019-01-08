package io.github.ovso.blackbox

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.github.ovso.blackbox.di.DaggerAppComponent
import io.github.ovso.blackbox.utils.AppInitUtils
import lombok.Getter

class App : DaggerApplication() {

  override fun onCreate() {
    super.onCreate()
    instance = this
    AppInitUtils.crashlytics(this)
    AppInitUtils.timer()
    AppInitUtils.ads(this)
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder()
        .application(this)
        .build()
  }

  companion object {
    @Getter var instance: Application? = null
      private set
  }
}