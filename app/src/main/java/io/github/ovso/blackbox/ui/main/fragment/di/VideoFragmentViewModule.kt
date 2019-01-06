package io.github.ovso.blackbox.ui.main.fragment.di

import dagger.Binds
import dagger.Module
import io.github.ovso.blackbox.ui.main.fragment.VideoFragment
import io.github.ovso.blackbox.ui.main.fragment.VideoFragmentPresenter

@Module
abstract class VideoFragmentViewModule {
  @Binds
  internal abstract fun bindVideoFragmentView(fragment: VideoFragment): VideoFragmentPresenter.View
}