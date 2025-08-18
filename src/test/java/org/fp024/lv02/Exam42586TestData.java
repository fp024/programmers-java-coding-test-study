package org.fp024.lv02;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam42586TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {93, 30, 55}, //
            new int[] {1, 30, 5},
            new int[] {2, 1}
            //
            ),
        Arguments.of(
            new int[] {95, 90, 99, 99, 80, 99}, //
            new int[] {1, 1, 1, 1, 1, 1},
            new int[] {1, 3, 2}
            //
            )
        //
        );
  }
}
