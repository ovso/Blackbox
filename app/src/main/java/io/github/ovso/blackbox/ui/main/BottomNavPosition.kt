package io.github.ovso.blackbox.ui.main

import androidx.fragment.app.Fragment
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.ui.main.fragment.VideoFragment

enum class BottomNavPosition(
  val position: Int,
  val id: Int,
  val tag: String
) {
  BLACKBOX(0, R.id.action_black_box, "BLACK_BOX"),
  MISTAKE_RATIO(1, R.id.action_mis_ratio, "MISTAKE_RATIO"),
  COPING_WITH_ACCIDENTS(2, R.id.action_copi_with, "COPING_WITH_ACCIDENTS"),
  OVERSEAS_BLACKBOX(3, R.id.action_oversease_black_box, "OVERSEAS_BLACK_BOX")
}

fun findNavigationPositionById(id: Int): BottomNavPosition = when (id) {
  BottomNavPosition.BLACKBOX.id -> BottomNavPosition.BLACKBOX
  BottomNavPosition.MISTAKE_RATIO.id -> BottomNavPosition.MISTAKE_RATIO
  BottomNavPosition.COPING_WITH_ACCIDENTS.id -> BottomNavPosition.COPING_WITH_ACCIDENTS
  else -> BottomNavPosition.OVERSEAS_BLACKBOX
}

fun BottomNavPosition.createFragment(): Fragment = when (this) {
  BottomNavPosition.BLACKBOX -> VideoFragment.newInstance(0)
  BottomNavPosition.MISTAKE_RATIO -> VideoFragment.newInstance(1)
  BottomNavPosition.COPING_WITH_ACCIDENTS -> VideoFragment.newInstance(2)
  BottomNavPosition.OVERSEAS_BLACKBOX -> VideoFragment.newInstance(3)
}

fun BottomNavPosition.getTag(): String = when (this) {
  BottomNavPosition.BLACKBOX -> BottomNavPosition.BLACKBOX.tag
  BottomNavPosition.MISTAKE_RATIO -> BottomNavPosition.MISTAKE_RATIO.tag
  BottomNavPosition.COPING_WITH_ACCIDENTS -> BottomNavPosition.COPING_WITH_ACCIDENTS.tag
  BottomNavPosition.OVERSEAS_BLACKBOX -> BottomNavPosition.OVERSEAS_BLACKBOX.tag
}
