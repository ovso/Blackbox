package io.github.ovso.blackbox.ui.main.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import io.github.ovso.blackbox.data.network.model.SearchItem;
import io.github.ovso.blackbox.ui.base.interfaces.OnRecyclerViewItemClickListener;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder>
    implements VideoAdapterView, VideoAdapterDataModel<SearchItem> {
  @Setter protected OnRecyclerViewItemClickListener
      onRecyclerViewItemClickListener;
  private List<SearchItem> items = new ArrayList<>();

  @NonNull @Override
  public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    return VideoViewHolder.create(viewGroup);
  }

  @Override
  public void onBindViewHolder(@NonNull VideoViewHolder viewHolder, int position) {
    if (viewHolder instanceof Bindable) {
      viewHolder.bind(getItem(position));
      viewHolder.setOnRecyclerViewItemClickListener(onRecyclerViewItemClickListener);
    }
  }

  @Override public int getItemCount() {
    return getSize();
  }

  @Override public int getSize() {
    return items.size();
  }

  @Override public void addAll(List<SearchItem> $items) {
    items.addAll($items);
  }

  @Override public SearchItem getItem(int position) {
    return items.get(position);
  }

  @Override public void clear() {
    items.clear();
  }

  @Override public void refresh() {
    notifyDataSetChanged();
  }

  @Override public void refresh(int positionStart, int itemCount) {
    notifyItemRangeInserted(positionStart, itemCount);
  }
}