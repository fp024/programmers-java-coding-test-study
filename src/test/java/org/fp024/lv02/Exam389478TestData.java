package org.fp024.lv02;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam389478TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            10, //
            2,
            new int[] {4, 3}
            //
            ),
        Arguments.of(
            8, //
            1,
            new int[] {3, 3}
            //
            ),
        Arguments.of(
            24, //
            24,
            new int[] {8, 6}
            //
            )
        //
        );
  }
}
