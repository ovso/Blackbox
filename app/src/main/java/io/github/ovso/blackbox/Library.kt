package io.github.ovso.blackbox

import android.content.Context
import com.google.android.gms.ads.MobileAds
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import timber.log.Timber


object Library {
  fun init(context: Context) {
    logger()
    timber()
    ads(context)
  }

  private fun logger() {
    Logger.addLogAdapter(object : AndroidLogAdapter() {
      override fun isLoggable(priority: Int, tag: String?): Boolean {
        return BuildConfig.DEBUG
      }
      })
  }

  private fun timber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(Timber.DebugTree())
    }
  }

  private fun ads(context: Context) {
    MobileAds.initialize(context, Ads.APP_ID)
  }

}
