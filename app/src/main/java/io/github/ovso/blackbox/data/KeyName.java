package io.github.ovso.blackbox.data;

import io.github.ovso.blackbox.data.network.SearchRequest_Factory;

public enum KeyName {
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

  private String value;

  KeyName(String $value) {
    this.value = $value;
  }

  public String get() {
    return value;
  }
}