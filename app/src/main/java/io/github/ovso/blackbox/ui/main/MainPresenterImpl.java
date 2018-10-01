package io.github.ovso.blackbox.ui.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import io.github.ovso.blackbox.R;
import io.github.ovso.blackbox.data.NavMenu;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

public class MainPresenterImpl implements MainPresenter {

  private MainPresenter.View view;
  private CompositeDisposable compositeDisposable;
  private String language;

  @Inject public MainPresenterImpl(MainPresenter.View view, String $language) {
    this.view = view;
    language = $language;
    this.compositeDisposable = new CompositeDisposable();
    view.changeTheme();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    view.setListener();

    setupBottomNav();
    view.showBlackBox(NavMenu.BLACK_BOX);
  }

  private void setupBottomNav() {
    if (!language.equalsIgnoreCase("ko")) {
      view.removeBottomNavMenu();
    }
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
        case R.id.action_oversease_black_box:
          view.showCopiWith(NavMenu.COPI_WITH);
          view.showOverSeas(NavMenu.OVER_SEAS);
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
