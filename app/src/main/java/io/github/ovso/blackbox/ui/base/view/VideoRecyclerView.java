package io.github.ovso.blackbox.ui.base.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import io.github.ovso.blackbox.ui.base.interfaces.OnEndlessRecyclerScrollListener;
import io.github.ovso.blackbox.ui.base.interfaces.OnRecyclerViewItemClickListener;
import io.github.ovso.blackbox.ui.main.fragment.adapter.VideoAdapter;
import io.github.ovso.blackbox.utils.ObjectUtils;
import lombok.Getter;

public class VideoRecyclerView extends RecyclerView {
  private OnRecyclerViewItemClickListener
      onRecyclerViewItemClickListener;
  @Getter private OnEndlessRecyclerScrollListener onEndlessRecyclerScrollListener;
  public VideoRecyclerView(@NonNull Context context) {
    super(context);
  }

  public VideoRecyclerView(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public VideoRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override public void setAdapter(Adapter adapter) {
    super.setAdapter(adapter);
    setOnItemClickListener(adapter);
  }

  public void setOnItemClickListener(
      OnRecyclerViewItemClickListener listener) {
    onRecyclerViewItemClickListener = listener;
    setOnItemClickListener(getAdapter());
  }

  private void setOnItemClickListener(Adapter adapter) {
    if (!ObjectUtils.isEmpty(adapter)) {
      if ((adapter instanceof VideoAdapter)) {
        ((VideoAdapter) adapter).setOnRecyclerViewItemClickListener(
            onRecyclerViewItemClickListener);
      }
    }
  }

  @Override public void addOnScrollListener(@NonNull OnScrollListener listener) {
    super.addOnScrollListener(listener);
    onEndlessRecyclerScrollListener = (OnEndlessRecyclerScrollListener) listener;
  }
}
