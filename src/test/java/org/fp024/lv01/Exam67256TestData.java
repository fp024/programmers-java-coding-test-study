package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam67256TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, //
            "right",
            "LRLLLRLLRRL"
            //
            ),
        Arguments.of(
            new int[] {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, //
            "left",
            "LRLLRRLLLRR"
            //
            ),
        Arguments.of(
            new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, //
            "right",
            "LLRLLRLLRL"
            //
            )
        //
        );
  }
}
