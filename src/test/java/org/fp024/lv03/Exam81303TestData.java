package org.fp024.lv03;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
class Exam81303TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            8, //
            2,
            new String[] {
              "D 2", //
              "C", //
              "U 3", //
              "C", //
              "D 4", //
              "C", //
              "U 2", //
              "Z", //
              "Z"
            },
            "OOOOXOOO"
            //
            ),
        Arguments.of(
            8, //
            2,
            new String[] {
              "D 2", //
              "C", //
              "U 3", //
              "C", //
              "D 4", //
              "C", //
              "U 2", //
              "Z", //
              "Z", //
              "U 1", //
              "C"
            },
            "OOXOXOOO"
            //
            )
        //
        );
  }
}
