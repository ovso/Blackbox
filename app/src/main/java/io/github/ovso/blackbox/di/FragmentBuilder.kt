package io.github.ovso.blackbox.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import io.github.ovso.blackbox.ui.main.fragment.VideoFragment
import io.github.ovso.blackbox.ui.main.fragment.di.VideoFragmentModule
import io.github.ovso.blackbox.ui.main.fragment.di.VideoFragmentViewModule
import javax.inject.Singleton

@Module(includes = arrayOf(AndroidSupportInjectionModule::class))
abstract class FragmentBuilder {
  @Singleton
  @ContributesAndroidInjector(
      modules = arrayOf(VideoFragmentModule::class, VideoFragmentViewModule::class)
  )
  internal abstract fun contributeRepoFragment(): VideoFragment
}