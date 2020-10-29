package io.github.ovso.blackbox.data.args

import androidx.annotation.StringRes

data class VideoArgs(
  val query: String,
  @StringRes val resId: Int
)
