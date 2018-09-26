package io.github.ovso.blackbox.ui.main.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import butterknife.BindView;
import io.github.ovso.blackbox.R;
import io.github.ovso.blackbox.data.network.model.SearchItem;
import io.github.ovso.blackbox.main.base.view.BaseFragment;
import io.github.ovso.blackbox.ui.base.interfaces.OnEndlessRecyclerScrollListener;
import io.github.ovso.blackbox.ui.base.interfaces.OnRecyclerViewItemClickListener;
import io.github.ovso.blackbox.ui.base.view.VideoRecyclerView;
import io.github.ovso.blackbox.ui.main.fragment.adapter.VideoAdapter;
import io.github.ovso.blackbox.ui.main.fragment.adapter.VideoAdapterView;
import io.github.ovso.blackbox.ui.video.LandscapeVideoActivity;
import io.github.ovso.blackbox.ui.video.PortraitVideoActivity;
import javax.inject.Inject;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public class VideoFragment extends BaseFragment implements VideoFragmentPresenter.View,
    OnRecyclerViewItemClickListener<SearchItem>,
    OnEndlessRecyclerScrollListener.OnLoadMoreListener {

  @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;
  @BindView(R.id.recycler_view) VideoRecyclerView recyclerView;
  @Inject VideoFragmentPresenter presenter;
  @Inject VideoAdapter adapter;
  @Inject VideoAdapterView adapterView;

  public static Fragment newInstance(Bundle args) {
    VideoFragment f = new VideoFragment();
    f.setArguments(args);
    return f;
  }

  @Override protected int getLayoutResID() {
    return R.layout.fragment_video;
  }

  @Override protected void onActivityCreate(Bundle savedInstanceState) {
    presenter.onActivityCreated(getArguments());
  }

  @Override public void onDestroyView() {
    presenter.onDestroyView();
    super.onDestroyView();
  }

  @Override public void setupRecyclerView() {
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setOnItemClickListener(this);
    ScaleInAnimationAdapter animationAdapter = new ScaleInAnimationAdapter(adapter);
    animationAdapter.setDuration(150);
    animationAdapter.setFirstOnly(false);
    recyclerView.addOnScrollListener(
        new OnEndlessRecyclerScrollListener
            .Builder((LinearLayoutManager) recyclerView.getLayoutManager(), this)
            .setVisibleThreshold(20)
            .build()
    );
    recyclerView.setAdapter(animationAdapter);
  }

  @Override public void refresh() {
    adapterView.refresh();
  }

  @Override public void showVideoTypeDialog(DialogInterface.OnClickListener onClickListener) {
    new AlertDialog.Builder(getContext()).setMessage(R.string.please_select_the_player_mode)
        .setPositiveButton(R.string.portrait_mode,
            onClickListener)
        .setNeutralButton(R.string.landscape_mode, onClickListener)
        .setNegativeButton(android.R.string.cancel, onClickListener)
        .show();
  }

  @Override public void showPortraitVideo(String videoId) {
    Intent intent = new Intent(getContext(), PortraitVideoActivity.class);
    intent.putExtra("video_id", videoId);
    startActivity(intent);
  }

  @Override public void showLandscapeVideo(String videoId) {
    Intent intent = new Intent(getContext(), LandscapeVideoActivity.class);
    intent.putExtra("video_id", videoId);
    startActivity(intent);
  }

  @Override public void showYoutubeUseWarningDialog() {
    new AlertDialog.Builder(getActivity()).setMessage(R.string.youtube_use_warning)
        .setPositiveButton(android.R.string.ok, null)
        .show();
  }

  @Override public void setLoaded() {
    recyclerView.getOnEndlessRecyclerScrollListener().setLoaded();
  }

  @Override public void setupSwipeRefresh() {
    swipeRefreshLayout.setOnRefreshListener(() -> presenter.onRefresh());
  }

  @Override public void hideLoading() {
    swipeRefreshLayout.setRefreshing(false);
  }

  @Override public void showLoading() {
    swipeRefreshLayout.setRefreshing(true);
  }

  @Override public void onItemClick(View view, SearchItem data, int itemPosition) {
    presenter.onItemClick(data);
  }

  @Override public void onLoadMore() {
    presenter.onLoadMore();
  }
}