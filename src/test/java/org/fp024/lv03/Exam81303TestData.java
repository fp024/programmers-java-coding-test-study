package org.fp024.lv03;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam81303TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            8, //
            2,
            new String[] {
              "D 2", //
              "C", //
              "U 3", //
              "C", //
              "D 4", //
              "C", //
              "U 2", //
              "Z", //
              "Z"
            },
            "OOOOXOOO"
            //
            ),
        Arguments.of(
            8, //
            2,
            new String[] {
              "D 2", //
              "C", //
              "U 3", //
              "C", //
              "D 4", //
              "C", //
              "U 2", //
              "Z", //
              "Z", //
              "U 1", //
              "C"
            },
            "OOXOXOOO"
            //
            )
        //
        );
  }
}
