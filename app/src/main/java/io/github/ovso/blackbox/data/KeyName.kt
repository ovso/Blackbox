package io.github.ovso.blackbox.data

enum class KeyName private constructor(private val value: String) {
  POSITION("position"),
  PAGE_TOKEN("pageToken"),
  Q("q"),
  MAX_RESULTS("maxResults"),
  ORDER("order"),
  TYPE("type"),
  VIDEO_SYNDICATED("videoSyndicated"),
  KEY("key"),
  PART("part"),
  NAV_MENU("nav_menu"),
  CHANNEL_ID("channelId"),
  RELATED_TO_VIDEO_ID("relatedToVideoId");

  fun get(): String {
    return value
  }
}