package io.github.ovso.blackbox.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.orhanobut.logger.Logger
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.ui.base.view.BaseActivity
import javax.inject.Inject

class MainActivity : BaseActivity(), MainPresenter.View {
  override val layoutResID: Int
    get() = R.layout.activity_main2

  var presenter: MainPresenter? = null
    @Inject set

  override fun onCreated(savedInstanceState: Bundle?) {
    presenter?.onCreate(savedInstanceState)
    setupNavigation()
  }

  private fun setupNavigation() {
    val navView: BottomNavigationView = findViewById(R.id.bnv_main)
    val navController = findNavController(R.id.nav_host_fragment)
    val appBarConfiguration = AppBarConfiguration(
      setOf(
        R.id.navigation_blackbox,
        R.id.navigation_mis_ratio,
        R.id.navigation_copi_with,
        R.id.navigation_black_box_overseas
      )
    )
    setupActionBarWithNavController(navController, appBarConfiguration)
    navView.setupWithNavController(navController)
    navView.setOnNavigationItemReselectedListener {
      Logger.d(it.toString())
    }
  }


  override fun closeDrawer() {
//    drawer_layout.closeDrawer(GravityCompat.START)
  }


  override fun showMessage(resId: Int) {}

  override fun showMessage(msg: String) {}
}
