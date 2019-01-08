package io.github.ovso.blackbox.ui.main.fragment.adapter

interface VideoAdapterDataModel<T> {
  val size: Int

  fun addAll(items: List<T>)

  fun getItem(position: Int): T

  fun clear()
}