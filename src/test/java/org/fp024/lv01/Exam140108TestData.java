package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
class Exam140108TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            "banana", //
            3
            //
            ),
        Arguments.of(
            "abracadabra", //
            6
            //
            ),
        Arguments.of(
            "aaabbaccccabba", //
            3
            //
            )
        //
        );
  }

  static Stream<Arguments> extraDataProvider() {
    return Stream.of(
        Arguments.of(
            "aaba", //
            1
            //
            )
        //
        );
  }
}
