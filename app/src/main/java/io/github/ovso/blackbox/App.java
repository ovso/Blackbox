package io.github.ovso.blackbox;

import android.app.Application;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import io.github.ovso.blackbox.di.DaggerAppComponent;
import io.github.ovso.blackbox.utils.AppInitUtils;
import lombok.Getter;

public class App extends DaggerApplication {
  @Getter private static boolean debug;
  @Getter private static Application instance = null;

  @Override public void onCreate() {
    super.onCreate();
    instance = this;
    AppInitUtils.crashlytics(this, debug);
    AppInitUtils.timer();
    AppInitUtils.ads(this);
  }

  @Override protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().application(this).build();
  }
}