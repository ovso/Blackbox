package io.github.ovso.blackbox.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import io.github.ovso.blackbox.ui.main.fragment.VideoFragment;
import io.github.ovso.blackbox.ui.main.fragment.di.VideoFragmentModule;
import io.github.ovso.blackbox.ui.main.fragment.di.VideoFragmentViewModule;
import javax.inject.Singleton;

@Module(includes = { AndroidSupportInjectionModule.class }) public abstract class FragmentBuilder {
  @Singleton @ContributesAndroidInjector(modules = {
      VideoFragmentModule.class,
      VideoFragmentViewModule.class
  })
  abstract VideoFragment contributeRepoFragment();
}