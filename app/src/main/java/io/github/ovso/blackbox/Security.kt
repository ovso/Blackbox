package io.github.ovso.blackbox


enum class Security(var value: String) {

  KEY("AIzaSyC1DdSKLNh5UKASsno5_NZEMJuxEVq5cMk"),

  ADMOB_APP_ID(
      if (BuildConfig.DEBUG)
        "ca-app-pub-3940256099942544~3347511713"
      else
        "ca-app-pub-8679189423397017~6001151702"
  ),

  ADMOB_BANNER_UNIT_ID(
      if (BuildConfig.DEBUG)
        "ca-app-pub-3940256099942544/6300978111"
      else
        "ca-app-pub-8679189423397017/9401055258"
  ),

  ADMOB_INTERSTITIAL_UNIT_ID(
      if (BuildConfig.DEBUG)
        "ca-app-pub-3940256099942544/1033173712"
      else
        "ca-app-pub-8679189423397017/3849345675"
  )
}
