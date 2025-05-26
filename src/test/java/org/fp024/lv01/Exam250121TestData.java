package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam250121TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new int[][] {
              {1, 20300104, 100, 80}, //
              {2, 20300804, 847, 37},
              {3, 20300401, 10, 8}
            }, //
            "date",
            20300501,
            "remain",
            new int[][] { //
              {3, 20300401, 10, 8}, //
              {1, 20300104, 100, 80}
            }
            //
            )
        //
        );
  }
}
