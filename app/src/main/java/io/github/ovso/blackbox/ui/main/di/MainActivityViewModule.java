package io.github.ovso.blackbox.ui.main.di;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.blackbox.ui.main.MainActivity;
import io.github.ovso.blackbox.ui.main.MainPresenter;

@Module public abstract class MainActivityViewModule {

  @Binds abstract MainPresenter.View bindMainView(MainActivity activity);
}