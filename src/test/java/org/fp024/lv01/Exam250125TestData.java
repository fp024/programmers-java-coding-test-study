package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam250125TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new String[][] {
              {"blue", "red", "orange", "red"},
              {"red", "red", "blue", "orange"},
              {"blue", "orange", "red", "red"},
              {"orange", "orange", "red", "blue"}
            }, //
            1,
            1,
            2
            //
            ),
        //
        Arguments.of(
            new String[][] {
              {"yellow", "green", "blue"}, //
              {"blue", "green", "yellow"},
              {"yellow", "blue", "blue"}
            }, //
            0,
            1,
            1
            //
            )
        //
        );
  }
}
