package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam77484TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {44, 1, 0, 0, 31, 25}, //
            new int[] {31, 10, 45, 1, 6, 19},
            new int[] {3, 5}
            //
            ),
        Arguments.of(
            new int[] {0, 0, 0, 0, 0, 0}, //
            new int[] {38, 19, 20, 40, 15, 25},
            new int[] {1, 6}
            //
            ),
        Arguments.of(
            new int[] {45, 4, 35, 20, 3, 9}, //
            new int[] {20, 9, 3, 45, 4, 35},
            new int[] {1, 1}
            //
            )
        //
        );
  }
}
