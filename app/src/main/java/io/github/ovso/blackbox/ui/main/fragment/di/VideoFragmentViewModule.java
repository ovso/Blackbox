package io.github.ovso.blackbox.ui.main.fragment.di;

import dagger.Binds;
import dagger.Module;
import io.github.ovso.blackbox.ui.main.fragment.VideoFragment;
import io.github.ovso.blackbox.ui.main.fragment.VideoFragmentPresenter;

@Module public abstract class VideoFragmentViewModule {
  @Binds abstract VideoFragmentPresenter.View bindVideoFragmentView(VideoFragment fragment);
}