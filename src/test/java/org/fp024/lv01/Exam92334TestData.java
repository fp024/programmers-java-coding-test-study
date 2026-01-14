package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
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
