package io.github.ovso.blackbox.utils

import android.content.Context
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.google.android.gms.ads.MobileAds
import io.fabric.sdk.android.Fabric
import io.github.ovso.blackbox.BuildConfig
import io.github.ovso.blackbox.Security
import timber.log.Timber

object AppInitUtils {

  fun crashlytics(context: Context) {
    if (!BuildConfig.DEBUG) {
      val core = CrashlyticsCore.Builder()
          .disabled(!BuildConfig.DEBUG)
          .build()
      Fabric.with(context, Crashlytics.Builder().core(core).build())
    }
  }

  fun timer() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  fun ads(context: Context) {
    MobileAds.initialize(context, Security.ADMOB_APP_ID.value)
  }
}
