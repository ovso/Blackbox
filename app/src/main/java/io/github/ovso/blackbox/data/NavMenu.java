package io.github.ovso.blackbox.data;

import lombok.Getter;

@Getter public enum NavMenu {
  BLACK_BOX(0),
  MIS_RATIO(1),
  COPI_WITH(2);

  private int position;

  NavMenu(int $position) {
    position = $position;
  }

}