package io.github.ovso.blackbox.ui.main.di;

import dagger.Module;
import dagger.Provides;
import io.github.ovso.blackbox.ui.main.MainPresenter;
import io.github.ovso.blackbox.ui.main.MainPresenterImpl;
import io.github.ovso.blackbox.utils.ResourceProvider;
import io.github.ovso.blackbox.utils.SchedulersFacade;
import javax.inject.Singleton;

@Module public class MainActivityModule {

  @Singleton @Provides MainPresenter provideMainPresenter(MainPresenter.View view,
      ResourceProvider resourceProvider, SchedulersFacade schedulersFacade) {
    return new MainPresenterImpl(view, resourceProvider, schedulersFacade);
  }
}