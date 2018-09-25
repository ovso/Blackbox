package io.github.ovso.blackbox.data.network;

import lombok.Getter;

@Getter public enum ApiEndPoint {
  SEARCH("https://www.googleapis.com/");

  ApiEndPoint(String $value) {
    url = $value;
  }

  private String url;
}