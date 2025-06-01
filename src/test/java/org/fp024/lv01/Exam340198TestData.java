package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam340198TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {5, 3, 2}, //
            new String[][] {
              {"A", "A", "-1", "B", "B", "B", "B", "-1"},
              {"A", "A", "-1", "B", "B", "B", "B", "-1"},
              {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
              {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"},
              {"D", "D", "-1", "-1", "-1", "-1", "-1", "F"},
              {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}
            },
            3
            //
            )
        //
        );
  }

  static Stream<Arguments> extraDataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {4, 5, 3, 6, 1}, //
            new String[][] {
              {"-1", "-1", "-1", "-1", "-1"},
              {"-1", "-1", "-1", "-1", "-1"},
              {"-1", "-1", "A", "-1", "-1"},
              {"-1", "-1", "-1", "-1", "-1"},
              {"-1", "-1", "-1", "-1", "-1"},
            },
            1
            //
            )
        //
        );
  }
}
