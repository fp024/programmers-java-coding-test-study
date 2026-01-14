package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
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
