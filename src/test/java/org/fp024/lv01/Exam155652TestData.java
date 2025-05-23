package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam155652TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            "aukks", //
            "wbqd", //
            5, //
            "happy"
            //
            ));
  }

  static Stream<Arguments> extraDataProvider() {
    return Stream.of(
        Arguments.of(
            "z", //
            "a", //
            1, //
            "b"
            //
            ),
        Arguments.of(
            "a", //
            "bcdefghijk", //
            20, //
            "o"
            //
            ),
        Arguments.of(
            "z", //
            "abcdefghij", //
            20, //
            "n"
            //
            ),
        Arguments.of(
            "z", //
            "abcdefghij", //
            20, //
            "n"
            //
            )
        //

        );
  }
}
