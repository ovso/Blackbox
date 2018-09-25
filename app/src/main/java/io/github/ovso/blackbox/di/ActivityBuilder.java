package io.github.ovso.blackbox.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.github.ovso.blackbox.ui.main.MainActivity;
import io.github.ovso.blackbox.ui.main.di.MainActivityModule;
import io.github.ovso.blackbox.ui.main.di.MainActivityViewModule;
import javax.inject.Singleton;

@Module(includes = { AndroidSupportInjectionModule.class })
public abstract class ActivityBuilder {
  @Singleton @ContributesAndroidInjector(modules = {
      MainActivityModule.class,
      MainActivityViewModule.class
  })
  abstract MainActivity bindMainActivity();
}
