package io.github.ovso.blackbox.ui.main.di

import dagger.Module
import dagger.Provides
import io.github.ovso.blackbox.ui.main.MainPresenter
import io.github.ovso.blackbox.ui.main.MainPresenterImpl
import java.util.Locale
import javax.inject.Singleton

@Module
class MainActivityModule {

  @Singleton @Provides fun provideMainPresenter(view: MainPresenter.View): MainPresenter {
    return MainPresenterImpl(view, Locale.getDefault().language)
  }
}