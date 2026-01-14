package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
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
