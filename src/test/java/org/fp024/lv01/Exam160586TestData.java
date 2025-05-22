package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam160586TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {"ABACD", "BCEFD"}, //
            new String[] {"ABCD", "AABB"}, //
            new int[] {9, 4}
            //
            ),
        //
        Arguments.of(
            new String[] {"AA"}, //
            new String[] {"B"}, //
            new int[] {-1}
            //
            ),
        //
        Arguments.of(
            new String[] {"AGZ", "BSSS"}, //
            new String[] {"ASA", "BGZ"}, //
            new int[] {4, 6}
            //
            )
        //
        );
  }

  // cspell:enable
}
