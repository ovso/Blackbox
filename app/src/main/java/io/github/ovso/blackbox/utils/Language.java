package io.github.ovso.blackbox.utils;

public enum Language {
  KO("ko"), EN("en");

  private String value;

  Language(String $value) {
    this.value = $value;
  }

  public String get() {
    return value;
  }
}
