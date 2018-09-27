package io.github.ovso.blackbox.ui.main.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.blackbox.ui.main.MainPresenter;
import io.github.ovso.blackbox.ui.main.MainPresenterImpl;
import java.util.Locale;
import javax.inject.Singleton;

@Module public class MainActivityModule {

  @Singleton @Provides MainPresenter provideMainPresenter(MainPresenter.View view) {
    return new MainPresenterImpl(view, Locale.getDefault().getLanguage());
  }
}