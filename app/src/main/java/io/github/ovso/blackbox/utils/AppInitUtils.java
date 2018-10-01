package io.github.ovso.blackbox.utils;

import android.content.Context;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.google.android.gms.ads.MobileAds;
import io.fabric.sdk.android.Fabric;
import io.github.ovso.blackbox.BuildConfig;
import io.github.ovso.blackbox.Security;
import timber.log.Timber;

public class AppInitUtils {

  private AppInitUtils() {

  }

  public static void crashlytics(Context context) {
    CrashlyticsCore core = new CrashlyticsCore.Builder().disabled(!BuildConfig.DEBUG).build();
    Fabric.with(context, new Crashlytics.Builder().core(core).build());
  }

  public static void timer() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  public static void ads(Context context) {
    MobileAds.initialize(context, Security.ADMOB_APP_ID.getValue());
  }
}
