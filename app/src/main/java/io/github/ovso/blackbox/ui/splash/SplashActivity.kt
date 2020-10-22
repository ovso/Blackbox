package io.github.ovso.blackbox.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.github.ovso.blackbox.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Intent(this, MainActivity::class.java).apply {
      startActivity(this)
      finish()
    }
  }
}
