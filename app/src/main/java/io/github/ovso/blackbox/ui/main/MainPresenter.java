package io.github.ovso.blackbox.ui.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import io.github.ovso.blackbox.data.NavMenu;

public interface MainPresenter {

  void onCreate(Bundle savedInstanceState);

  boolean onNavItemSelected(@IdRes int itemId);

  void onBackPressed(boolean isDrawerOpen);

  boolean onBottomNavItemSelected(@IdRes int itemId, boolean isChecked);

  interface View {

    void setListener();

    void closeDrawer();

    void finish();

    void showBlackBox(NavMenu menu);

    void showMisRatio(NavMenu menu);

    void showCopiWith(NavMenu menu);

    void showMessage(@StringRes int resId);

    void showMessage(String msg);

    void changeTheme();

    void removeBottomNavMenu();

    void showOverSeas(NavMenu menu);
  }
}
