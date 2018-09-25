package io.github.ovso.blackbox.ui.main.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import io.github.ovso.blackbox.data.network.model.SearchItem;

public interface VideoFragmentPresenter {

  void onActivityCreated(Bundle args);

  void onDestroyView();

  void onItemClick(SearchItem data);

  void onLoadMore();

  void onRefresh();

  interface View {

    void setupRecyclerView();

    void refresh();

    void showVideoTypeDialog(DialogInterface.OnClickListener onClickListener);

    void showPortraitVideo(String videoId);

    void showLandscapeVideo(String videoId);

    void showYoutubeUseWarningDialog();

    void setLoaded();

    void setupSwipeRefresh();

    void hideLoading();

    void showLoading();
  }
}
