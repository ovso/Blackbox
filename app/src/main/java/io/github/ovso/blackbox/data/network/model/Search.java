package io.github.ovso.blackbox.data.network.model;

import java.util.List;
import lombok.Getter;

@Getter public class Search {
  private String kind;
  private String etag;
  private String nextPageToken;
  private String regionCode;
  private SearchPageInfo pageInfo;
  private List<SearchItem> items;
}