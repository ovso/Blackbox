package io.github.ovso.blackbox.ui.main

import android.content.DialogInterface
import android.os.Bundle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import io.github.ovso.blackbox.Ads
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.exts.attach
import io.github.ovso.blackbox.exts.detach
import io.github.ovso.blackbox.ui.base.view.BaseActivity
import io.github.ovso.commons.ads.NativeAdsDialog
import io.github.ovso.commons.ads.navigateToStore
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
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
//    val toggle = ActionBarDrawerToggle(
//        this, drawer_layout, toolbar, R.string.navigation_drawer_open,
//        R.string.navigation_drawer_close
//    )
    //drawer.addDrawerListener(toggle);
    //toggle.syncState();
    navigation_view.setNavigationItemSelectedListener { item ->
      presenter!!.onNavItemSelected(
        item.itemId
      )
    }
//    bottom_navigation_view.setOnNavigationItemSelectedListener { item ->
//      val isChecked = bottom_navigation_view.menu.findItem(item.itemId)
//        .isChecked
//      presenter!!.onBottomNavItemSelected(item.itemId, isChecked)
//    }
    bottom_navigation_view.setOnNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.action_black_box -> switchFragment(BottomNavPosition.BLACKBOX)
        R.id.action_mis_ratio -> switchFragment(BottomNavPosition.MISTAKE_RATIO)
        R.id.action_copi_with -> switchFragment(BottomNavPosition.COPING_WITH_ACCIDENTS)
        R.id.action_oversease_black_box -> switchFragment(BottomNavPosition.OVERSEAS_BLACKBOX)
      }
      true
    }
    switchFragment(BottomNavPosition.BLACKBOX)
  }

  private fun switchFragment(navPosition: BottomNavPosition): Boolean {
    return findFragment(navPosition).let {
      if (it.isAdded) return false
      supportFragmentManager.detach(R.id.fragment_container) // Extension function
      supportFragmentManager.attach(
        it,
        navPosition.getTag(),
        R.id.fragment_container
      ) // Extension function
      supportFragmentManager.executePendingTransactions()
    }
  }


  private fun findFragment(position: BottomNavPosition): Fragment {
    return supportFragmentManager.findFragmentByTag(position.getTag()) ?: position.createFragment()
  }

  override fun closeDrawer() {
    drawer_layout.closeDrawer(GravityCompat.START)
  }

  override fun onBackPressed() {
    presenter!!.onBackPressed(drawer_layout.isDrawerOpen(GravityCompat.START))
  }

  override fun showMessage(resId: Int) {}

  override fun showMessage(msg: String) {}

  override fun removeBottomNavMenu() {
    bottom_navigation_view.menu.removeItem(R.id.action_mis_ratio)
    bottom_navigation_view.menu.removeItem(R.id.action_copi_with)
    bottom_navigation_view.menu.removeItem(R.id.action_oversease_black_box)
  }

  override fun showNativeAdsDialog() {

    fun onDialogClick(dialog: DialogInterface, which: Int) {
      dialog.dismiss()
      when (which) {
        DialogInterface.BUTTON_POSITIVE -> finish()
        else -> {
          navigateToStore()
          finish()
        }
      }
    }

    NativeAdsDialog(this)
      .setUnitId(Ads.NATIVE_UNIT_ID)
      .setCancelable(false)
      .setPositiveButton(R.string.quit_app, ::onDialogClick)
      .setNeutralButton(R.string.leave_review, ::onDialogClick)
      .show()
  }
}
