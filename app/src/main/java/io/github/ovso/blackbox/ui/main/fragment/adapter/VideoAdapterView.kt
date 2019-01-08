package io.github.ovso.blackbox.ui.main.fragment.adapter

interface VideoAdapterView {
  fun refresh()

  fun refresh(
    positionStart: Int,
    itemCount: Int
  )
}
