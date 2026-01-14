package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
class Exam131128TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            "100", //
            "2345", //
            "-1"
            //
            ),
        Arguments.of(
            "100", //
            "203045", //
            "0"
            //
            ),
        Arguments.of(
            "100", //
            "123450", //
            "10"
            //
            ),
        Arguments.of(
            "12321", //
            "42531", //
            "321"
            //
            ),
        Arguments.of(
            "5525", //
            "1255", //
            "552"
            //
            )

        //
        );
  }
}
