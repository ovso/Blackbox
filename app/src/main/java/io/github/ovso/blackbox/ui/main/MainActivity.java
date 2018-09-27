package io.github.ovso.blackbox.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.widget.FrameLayout;
import butterknife.BindView;
import io.github.ovso.blackbox.R;
import io.github.ovso.blackbox.data.KeyName;
import io.github.ovso.blackbox.data.NavMenu;
import io.github.ovso.blackbox.ui.base.view.BaseActivity;
import io.github.ovso.blackbox.ui.main.fragment.VideoFragment;
import javax.inject.Inject;

public class MainActivity extends BaseActivity
    implements MainPresenter.View {

  @Inject MainPresenter presenter;
  @BindView(R.id.drawer_layout) DrawerLayout drawer;
  @BindView(R.id.fragment_container) FrameLayout fragmentContainer;
  @BindView(R.id.bottom_navigation_view) BottomNavigationView bottomNavigationView;
  @BindView(R.id.navigation_view) NavigationView navigationView;

  @Override protected void onCreated(Bundle savedInstanceState) {
    presenter.onCreate(savedInstanceState);
  }

  @Override public void setListener() {
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    navigationView.setNavigationItemSelectedListener(
        item -> presenter.onNavItemSelected(item.getItemId()));
    bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
      boolean isChecked = bottomNavigationView.getMenu().findItem(item.getItemId()).isChecked();
      return presenter.onBottomNavItemSelected(item.getItemId(), isChecked);
    });
  }

  @Override public void closeDrawer() {
    drawer.closeDrawer(GravityCompat.START);
  }

  @Override public void showBlackBox(NavMenu menu) {
    getSupportFragmentManager().beginTransaction()
        .setCustomAnimations(R.animator.enter_animation, R.animator.exit_animation,
            R.animator.enter_animation, R.animator.exit_animation)
        .replace(R.id.fragment_container, VideoFragment.newInstance(createArgs(menu)))
        .commit();
  }

  @Override public void showMisRatio(NavMenu menu) {
    getSupportFragmentManager().beginTransaction()
        .setCustomAnimations(R.animator.enter_animation, R.animator.exit_animation,
            R.animator.enter_animation, R.animator.exit_animation)
        .replace(R.id.fragment_container, VideoFragment.newInstance(createArgs(menu)))
        .commit();
  }

  @Override public void showCopiWith(NavMenu menu) {
    Bundle args = new Bundle();
    args.putSerializable(KeyName.NAV_MENU.get(), menu);
    getSupportFragmentManager().beginTransaction()
        .setCustomAnimations(R.animator.enter_animation, R.animator.exit_animation,
            R.animator.enter_animation, R.animator.exit_animation)
        .replace(R.id.fragment_container, VideoFragment.newInstance(createArgs(menu)))
        .commit();
  }

  private Bundle createArgs(NavMenu menu) {
    Bundle args = new Bundle();
    args.putInt(KeyName.POSITION.get(), menu.getPosition());
    return args;
  }

  @Override protected int getLayoutResID() {
    return R.layout.activity_main;
  }

  @Override public void onBackPressed() {
    presenter.onBackPressed(drawer.isDrawerOpen(GravityCompat.START));
  }

  @Override public void showMessage(int resId) {
  }

  @Override public void showMessage(@NonNull String msg) {
  }

  @Override public void changeTheme() {
    setTheme(R.style.AppTheme_NoActionBar);
  }
}