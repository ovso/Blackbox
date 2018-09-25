package io.github.ovso.blackbox.utils;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;

public class SchedulersFacade {

  @Inject
  public SchedulersFacade() {
  }

  /**
   * IO thread pool scheduler
   */
  public Scheduler io() {
    return Schedulers.io();
  }

  /**
   * Computation thread pool scheduler
   */
  public Scheduler computation() {
    return Schedulers.computation();
  }

  /**
   * Main Thread scheduler
   */
  public Scheduler ui() {
    return AndroidSchedulers.mainThread();
  }
}
