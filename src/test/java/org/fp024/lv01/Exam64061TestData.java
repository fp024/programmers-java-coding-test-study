package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam64061TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new int[][] {
              {0, 0, 0, 0, 0}, //
              {0, 0, 1, 0, 3},
              {0, 2, 5, 0, 1},
              {4, 2, 4, 4, 2},
              {3, 5, 1, 3, 1}
            },
            new int[] {1, 5, 3, 5, 1, 2, 1, 4}, //
            4
            //
            )
        //
        );
  }
}
