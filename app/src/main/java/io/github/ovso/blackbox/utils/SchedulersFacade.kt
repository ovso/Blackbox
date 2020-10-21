package io.github.ovso.blackbox.utils

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class SchedulersFacade @Inject
constructor() {

  /**
   * IO thread pool scheduler
   */
  fun io(): Scheduler {
    return Schedulers.io()
  }

  /**
   * Main Thread scheduler
   */
  fun ui(): Scheduler {
    return AndroidSchedulers.mainThread()
  }
}
