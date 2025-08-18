package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam159994TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {"i", "drink", "water"}, //
            new String[] {"want", "to"},
            new String[] {"i", "want", "to", "drink", "water"},
            "Yes"
            //
            ),
        Arguments.of(
            new String[] {"i", "water", "drink"}, //
            new String[] {"want", "to"},
            new String[] {"i", "want", "to", "drink", "water"},
            "No"
            //
            )
        //
        );
  }
}
