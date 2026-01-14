package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
class Exam389478TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            22, // n
            6, // w
            8, // num
            3
            //
            ),
        Arguments.of(
            13, // n
            3, // w
            6, // num
            4
            //
            )
        //
        );
  }
}
