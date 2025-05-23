package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam42862TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            5, //
            new int[] {2, 4},
            new int[] {1, 3, 5},
            5
            //
            ),
        Arguments.of(
            5, //
            new int[] {2, 4},
            new int[] {3},
            4
            //
            ),
        Arguments.of(
            3, //
            new int[] {3},
            new int[] {1},
            2
            //
            )

        //
        );
  }
}
