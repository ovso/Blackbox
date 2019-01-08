package io.github.ovso.blackbox.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import io.github.ovso.blackbox.App

@Component(
    modules = arrayOf(
        AndroidSupportInjectionModule::class, AppModule::class, ActivityBuilder::class,
        FragmentBuilder::class
    )
)
interface AppComponent : AndroidInjector<DaggerApplication> {
  @Component.Builder
  interface Builder {
    @BindsInstance fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(app: App)
}