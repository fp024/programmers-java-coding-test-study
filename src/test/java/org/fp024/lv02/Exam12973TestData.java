package org.fp024.lv02;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam12973TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            "baabaa", //
            1
            //
            ),
        Arguments.of(
            "cdcd", //
            0
            //
            )
        //
        );
  }
}
