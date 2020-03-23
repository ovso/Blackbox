package io.github.ovso.blackbox.exts

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun FragmentManager.detach(containerId: Int) {
  findFragmentById(containerId)?.also {
    beginTransaction().detach(it)
      .commit()
  }
}

fun FragmentManager.attach(
  fragment: Fragment,
  tag: String,
  containerId: Int
) {
  if (fragment.isDetached) {
    beginTransaction().attach(fragment)
      .commit()
  } else {
    beginTransaction().add(containerId, fragment, tag)
      .commit()
  }
  // Set a transition animation for this transaction.
  beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
    .commit()
}
