package io.github.ovso.blackbox.data.network.model;

import lombok.Getter;

@Getter public class SearchItem {
  private String kind;
  private String etag;
  private SearchItemId id;
  private Snippet snippet;
}