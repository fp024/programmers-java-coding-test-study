package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam389478TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            22, // n
            6, // w
            8, // num
            3
            //
            ),
        Arguments.of(
            13, // n
            3, // w
            6, // num
            4
            //
            )
        //
        );
  }
}
