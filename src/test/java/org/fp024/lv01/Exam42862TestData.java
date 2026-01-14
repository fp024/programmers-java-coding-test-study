package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
class Exam42862TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            5, //
            new int[] {2, 4},
            new int[] {1, 3, 5},
            5
            //
            ),
        Arguments.of(
            5, //
            new int[] {2, 4},
            new int[] {3},
            4
            //
            ),
        Arguments.of(
            3, //
            new int[] {3},
            new int[] {1},
            2
            //
            )

        //
        );
  }
}
