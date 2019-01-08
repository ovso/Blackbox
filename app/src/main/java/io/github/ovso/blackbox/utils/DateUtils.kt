package io.github.ovso.blackbox.utils

import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {

  fun getDate(
    date: Date,
    format: String
  ): String {
    val simpleDateFormat = SimpleDateFormat(format)
    return simpleDateFormat.format(date)
  }
}