package io.github.ovso.blackbox.ui.main.fragment.adapter;

public interface VideoAdapterView {
  void refresh();

  void refresh(int positionStart, int itemCount);
}
