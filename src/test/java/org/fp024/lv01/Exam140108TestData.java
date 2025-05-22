package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam140108TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            "banana", //
            3
            //
            ),
        Arguments.of(
            "abracadabra", //
            6
            //
            ),
        Arguments.of(
            "aaabbaccccabba", //
            3
            //
            )
        //
        );
  }
}
