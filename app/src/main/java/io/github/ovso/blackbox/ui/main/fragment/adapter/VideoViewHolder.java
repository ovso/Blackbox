package io.github.ovso.blackbox.ui.main.fragment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import io.github.ovso.blackbox.R;
import io.github.ovso.blackbox.data.network.model.SearchItem;
import io.github.ovso.blackbox.ui.base.interfaces.OnRecyclerViewItemClickListener;
import io.github.ovso.blackbox.utils.DateUtils;
import io.github.ovso.blackbox.utils.ObjectUtils;
import java.util.Locale;
import lombok.Setter;

public class VideoViewHolder extends RecyclerView.ViewHolder implements Bindable<SearchItem> {
  @BindView(R.id.title_text_view) TextView titleTextView;
  @BindView(R.id.date_text_view) TextView dateTextView;
  @BindView(R.id.thumbnail_image_view) AppCompatImageView thumbnailImageView;

  private SearchItem data;
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
    titleTextView.setText($data.getSnippet().getTitle());

    String language = Locale.getDefault().getLanguage();
    String format;
    if (language.equalsIgnoreCase("ko")) {
      format = "yyyy년 MM월 dd일 HH시 mm분";
    } else {
      format = "yyyy/MM/dd HH:mm";
    }

    dateTextView.setText(
        DateUtils.getDate($data.getSnippet().getPublishedAt(), format));
  }

  @OnClick(R.id.play_button) void onClick(View view) {
    if (!ObjectUtils.isEmpty(onRecyclerViewItemClickListener)) {
      onRecyclerViewItemClickListener.onItemClick(view, data, 0);
    }
  }
}
