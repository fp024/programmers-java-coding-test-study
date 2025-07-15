package org.fp024.lv02;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam49994TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            "ULURRDLLU", //
            7
            //
            ),
        Arguments.of(
            "LULLLLLLU", //
            7
            //
            )
        //
        );
  }

  static Stream<Arguments> extraDataProvider() {
    return Stream.of(
        Arguments.of(
            "ULR", //
            2
            //
            )
        //
        );
  }
}
