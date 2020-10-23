package io.github.ovso.commons.ads

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

fun Fragment.navigateToStore() {
  Intent(Intent.ACTION_VIEW).apply {
    data =
      Uri.parse("https://play.google.com/store/apps/details?id=${requireContext().packageName}")
    setPackage("com.android.vending")
    startActivity(this)
  }
}
