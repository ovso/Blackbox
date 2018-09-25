package io.github.ovso.blackbox.data;

public enum KeyName {
  POSITION("position"),
  PAGE_TOKEN("pageToken"),
  Q("q"),
  MAX_RESULTS("maxResults"),
  ORDER("order"),
  TYPE("type"),
  VIDEO_SYNDICATED("videoSyndicated"),
  KEY("key"),
  PART("part");

  private String value;

  KeyName(String $value) {
    this.value = $value;
  }

  public String get() {
    return value;
  }
}