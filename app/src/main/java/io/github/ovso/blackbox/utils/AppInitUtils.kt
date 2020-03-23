package io.github.ovso.blackbox.utils

import android.content.Context
import com.google.android.gms.ads.MobileAds
import io.github.ovso.blackbox.BuildConfig
import io.github.ovso.blackbox.Security
import timber.log.Timber

object AppInitUtils {

  fun timer() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  fun ads(context: Context) {
    MobileAds.initialize(context, Security.ADMOB_APP_ID.value)
  }
}
