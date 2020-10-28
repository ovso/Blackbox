package io.github.ovso.blackbox.ui.main.fragment.di

import androidx.navigation.fragment.navArgs
import dagger.Module
import dagger.Provides
import io.github.ovso.blackbox.data.args.VideoArgs
import io.github.ovso.blackbox.data.network.SearchRequest
import io.github.ovso.blackbox.data.network.model.SearchItem
import io.github.ovso.blackbox.ui.main.fragment.VideoFragment
import io.github.ovso.blackbox.ui.main.fragment.VideoFragmentArgs
import io.github.ovso.blackbox.ui.main.fragment.VideoFragmentPresenter
import io.github.ovso.blackbox.ui.main.fragment.VideoFragmentPresenterImpl
import io.github.ovso.blackbox.ui.main.fragment.adapter.VideoAdapter
import io.github.ovso.blackbox.ui.main.fragment.adapter.VideoAdapterDataModel
import io.github.ovso.blackbox.ui.main.fragment.adapter.VideoAdapterView
import io.github.ovso.blackbox.utils.ResourceProvider
import io.github.ovso.blackbox.utils.SchedulersFacade
import javax.inject.Singleton

@Module
class VideoFragmentModule {

  @Singleton
  @Provides
  internal fun provideVideoFragmentPresenter(
    view: VideoFragmentPresenter.View,
    searchRequest: SearchRequest,
    schedulersFacade: SchedulersFacade,
    adapterDataModel: VideoAdapterDataModel<SearchItem>,
    args: VideoArgs
  ): VideoFragmentPresenter {
    return VideoFragmentPresenterImpl(
      view, searchRequest, schedulersFacade,
      adapterDataModel, args
    )
  }

  @Singleton
  @Provides
  internal fun provideVideoAdapter(): VideoAdapter {
    return VideoAdapter()
  }

  @Provides
  internal fun provideVideoAdapterView(adapter: VideoAdapter): VideoAdapterView {
    return adapter
  }

  @Provides
  internal fun provideVideoAdapterDataModel(adapter: VideoAdapter): VideoAdapterDataModel<SearchItem> {
    return adapter
  }

  @Provides
  fun provideArguments(fragment: VideoFragment): VideoArgs {
    val navArgs = fragment.navArgs<VideoFragmentArgs>()
    return VideoArgs(fragment.getString(navArgs.value.query))
  }
}
