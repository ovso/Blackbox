package io.github.ovso.blackbox.ui.main

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.data.KeyName
import io.github.ovso.blackbox.data.NavMenu
import io.github.ovso.blackbox.ui.base.view.BaseActivity
import io.github.ovso.blackbox.ui.main.fragment.VideoFragment
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.activity_main.navigation_view
import kotlinx.android.synthetic.main.app_bar_main.bottom_navigation_view
import kotlinx.android.synthetic.main.app_bar_main.toolbar
import javax.inject.Inject

class MainActivity : BaseActivity(), MainPresenter.View {
  override val layoutResID: Int
    get() = R.layout.activity_main

  var presenter: MainPresenter? = null
    @Inject set

  override fun onCreated(savedInstanceState: Bundle?) {
    presenter?.onCreate(savedInstanceState)
  }

  override fun setListener() {
    val toggle = ActionBarDrawerToggle(
        this, drawer_layout, toolbar, R.string.navigation_drawer_open,
        R.string.navigation_drawer_close
    )
    //drawer.addDrawerListener(toggle);
    //toggle.syncState();
    navigation_view.setNavigationItemSelectedListener { item ->
      presenter!!.onNavItemSelected(
          item.itemId
      )
    }
    bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
      val isChecked = bottom_navigation_view.menu.findItem(item.itemId)
          .isChecked
      presenter!!.onBottomNavItemSelected(item.itemId, isChecked)
    }
  }

  override fun closeDrawer() {
    drawer_layout.closeDrawer(GravityCompat.START)
  }

  override fun showBlackBox(menu: NavMenu) {
    supportFragmentManager.beginTransaction()
        .setCustomAnimations(
            R.animator.enter_animation, R.animator.exit_animation,
            R.animator.enter_animation, R.animator.exit_animation
        )
        .replace(R.id.fragment_container, VideoFragment.newInstance(createArgs(menu)))
        .commit()
  }

  override fun showMisRatio(menu: NavMenu) {
    supportFragmentManager.beginTransaction()
        .setCustomAnimations(
            R.animator.enter_animation, R.animator.exit_animation,
            R.animator.enter_animation, R.animator.exit_animation
        )
        .replace(R.id.fragment_container, VideoFragment.newInstance(createArgs(menu)))
        .commit()
  }

  override fun showCopiWith(menu: NavMenu) {
    supportFragmentManager.beginTransaction()
        .setCustomAnimations(
            R.animator.enter_animation, R.animator.exit_animation,
            R.animator.enter_animation, R.animator.exit_animation
        )
        .replace(R.id.fragment_container, VideoFragment.newInstance(createArgs(menu)))
        .commit()
  }

  override fun showOverSeas(menu: NavMenu) {
    supportFragmentManager.beginTransaction()
        .setCustomAnimations(
            R.animator.enter_animation, R.animator.exit_animation,
            R.animator.enter_animation, R.animator.exit_animation
        )
        .replace(R.id.fragment_container, VideoFragment.newInstance(createArgs(menu)))
        .commit()
  }

  private fun createArgs(menu: NavMenu): Bundle {
    val args = Bundle()
    args.putInt(KeyName.POSITION.get(), menu.position)
    return args
  }

  override fun onBackPressed() {
    presenter!!.onBackPressed(drawer_layout.isDrawerOpen(GravityCompat.START))
  }

  override fun showMessage(resId: Int) {}

  override fun showMessage(msg: String) {}

  override fun changeTheme() {
    setTheme(R.style.AppTheme_NoActionBar)
  }

  override fun removeBottomNavMenu() {
    bottom_navigation_view.menu.removeItem(R.id.action_mis_ratio)
    bottom_navigation_view.menu.removeItem(R.id.action_copi_with)
    bottom_navigation_view.menu.removeItem(R.id.action_oversease_black_box)
  }
}