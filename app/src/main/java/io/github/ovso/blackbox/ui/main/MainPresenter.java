package io.github.ovso.blackbox.ui.main;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

public interface MainPresenter {

  void onCreate(Bundle savedInstanceState);

  boolean onNavItemSelected(@IdRes int itemId);

  void onBackPressed(boolean isDrawerOpen);

  boolean onBottomNavItemSelected(@IdRes int itemId, boolean isChecked);

  interface View {

    void setListener();

    void closeDrawer();

    void finish();

    void showBlackBox();

    void showMisRatio();

    void showCopiWith();

    void showMessage(@StringRes int resId);

    void showMessage(String msg);

    void changeTheme();
  }
}
