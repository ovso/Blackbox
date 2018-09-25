package io.github.ovso.blackbox.ui.main.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import io.github.ovso.blackbox.R;
import io.github.ovso.blackbox.data.network.model.SearchItem;
import io.github.ovso.blackbox.ui.base.interfaces.OnRecyclerViewItemClickListener;
import lombok.Setter;

public class VideoViewHolder extends RecyclerView.ViewHolder implements Bindable<SearchItem> {
  private SearchItem data;
  @BindView(R.id.thumbnail_image_view) AppCompatImageView thumbnailImageView;
  @Setter private OnRecyclerViewItemClickListener
      onRecyclerViewItemClickListener;

  private VideoViewHolder(@NonNull View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public static VideoViewHolder create(ViewGroup parent) {
    return new VideoViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_video, parent, false));
  }

  @Override public void bind(SearchItem $data) {
    data = $data;
    Glide.with(itemView.getContext())
        .load($data.getSnippet().getThumbnails().getMedium().getUrl())
        .into(thumbnailImageView);
  }

  @OnClick(R.id.play_button) void onClick(View view) {
    onRecyclerViewItemClickListener.onItemClick(view, data, 0);
  }
}
