package org.fp024.lv02;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
class Exam389478TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            10, //
            2,
            new int[] {4, 3}
            //
            ),
        Arguments.of(
            8, //
            1,
            new int[] {3, 3}
            //
            ),
        Arguments.of(
            24, //
            24,
            new int[] {8, 6}
            //
            )
        //
        );
  }
}
