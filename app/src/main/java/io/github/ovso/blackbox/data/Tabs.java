package io.github.ovso.blackbox.data;

import lombok.Getter;

@Getter public enum Tabs {
  TAB0(0),
  TAB1(1),
  TAB2(2),
  TAB3(3),
  TAB4(4),
  TAB5(5),
  TAB6(6),
  TAB7(7),
  TAB8(8),
  TAB9(9),
  TAB10(10),
  TAB11(11);

  private int position;

  Tabs(int $position) {
    position = $position;
  }

}
