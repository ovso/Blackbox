package io.github.ovso.blackbox.ui.main.fragment.adapter;

import java.util.List;

public interface VideoAdapterDataModel<T> {
  int getSize();

  void addAll(List<T> items);

  T getItem(int position);

  void clear();
}
