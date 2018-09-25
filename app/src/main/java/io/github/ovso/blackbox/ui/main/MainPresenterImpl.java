package io.github.ovso.blackbox.ui.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import io.github.ovso.blackbox.R;
import io.github.ovso.blackbox.data.KeyName;
import io.github.ovso.blackbox.data.NavMenu;
import io.github.ovso.blackbox.utils.ResourceProvider;
import io.github.ovso.blackbox.utils.SchedulersFacade;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

public class MainPresenterImpl implements MainPresenter {

  private MainPresenter.View view;
  private CompositeDisposable compositeDisposable;
  private ResourceProvider resourceProvider;
  private SchedulersFacade schedulersFacade;

  @Inject public MainPresenterImpl(MainPresenter.View view, ResourceProvider $resourceProvider,
      SchedulersFacade $schedulers) {
    this.view = view;
    resourceProvider = $resourceProvider;
    schedulersFacade = $schedulers;
    this.compositeDisposable = new CompositeDisposable();
    view.changeTheme();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    view.setListener();
    view.showBlackBox(NavMenu.BLACK_BOX);
  }

  @Override public boolean onNavItemSelected(int itemId) {
    view.closeDrawer();
    return true;
  }

  @Override public boolean onBottomNavItemSelected(@IdRes int itemId, boolean isChecked) {
    if (!isChecked) {
      switch (itemId) {
        case R.id.action_black_box:
          view.showBlackBox(NavMenu.BLACK_BOX);
          break;
        case R.id.action_mis_ratio:
          view.showMisRatio(NavMenu.MIS_RATIO);
          break;
        case R.id.action_copi_with:
          view.showCopiWith(NavMenu.COPI_WITH);
          break;
      }
    }
    return true;
  }

  @Override public void onBackPressed(boolean isDrawerOpen) {
    if (isDrawerOpen) {
      view.closeDrawer();
    } else {
      compositeDisposable.clear();
      view.finish();
    }
  }
}
