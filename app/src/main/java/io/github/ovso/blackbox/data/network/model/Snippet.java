package io.github.ovso.blackbox.data.network.model;

import lombok.Getter;

@Getter public class Snippet {
  private String publishedAt;
  private String channelId;
  private String title;
  private String description;
  private SearchThumbnails thumbnails;
  private String channelTitle;
  private String liveBroadcastContent;
}