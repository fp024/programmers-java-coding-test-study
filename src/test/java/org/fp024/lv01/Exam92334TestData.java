package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam92334TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {"muzi", "frodo", "apeach", "neo"}, //
            new String[] {
              "muzi frodo", //
              "apeach frodo",
              "frodo neo",
              "muzi neo",
              "apeach muzi"
            },
            2,
            new int[] {2, 1, 1, 0}
            //
            ),
        Arguments.of(
            new String[] {"con", "ryan"}, //
            new String[] {
              "ryan con", //
              "ryan con",
              "ryan con",
              "ryan con"
            },
            3,
            new int[] {0, 0}
            //
            )

        //

        );
  }
}
