package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam133502TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {2, 1, 1, 2, 3, 1, 2, 3, 1}, //
            2
            //
            ),
        Arguments.of(
            new int[] {1, 3, 2, 1, 2, 1, 3, 1, 2}, //
            0
            //
            )

        //
        );
  }

  static Stream<Arguments> extraDataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {1, 2, 3, 1, 1, 2, 3, 1, 1, 2, 3, 1}, //
            3
            //
            ),
        Arguments.of(
            new int[] {1}, //
            0
            //
            ),
        Arguments.of(
            new int[] {1, 1, 2, 3, 1, 2, 3, 1}, //
            2
            //
            )

        //
        );
  }
}
