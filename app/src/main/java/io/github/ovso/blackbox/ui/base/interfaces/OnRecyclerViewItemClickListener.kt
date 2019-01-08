package io.github.ovso.blackbox.ui.base.interfaces

import android.view.View

interface OnRecyclerViewItemClickListener<T> {
  fun onItemClick(
    view: View,
    data: T,
    itemPosition: Int
  )
}