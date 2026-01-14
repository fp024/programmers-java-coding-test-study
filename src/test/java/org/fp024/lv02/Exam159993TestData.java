package org.fp024.lv02;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
class Exam159993TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {
              "SOOOL", //
              "XXXXO", //
              "OOOOO", //
              "OXXXX", //
              "OOOOE"
            }, //
            16
            //
            ),
        Arguments.of(
            new String[] {
              "LOOXS", //
              "OOOOX", //
              "OOOOO", //
              "OOOOO", //
              "EOOOO"
            }, //
            -1
            //
            ),
        // 💡 레버 찍고 되돌아오는 개념 포함
        Arguments.of(
            new String[] {
              "SOL", //
              "OXX", //
              "OOE"
            }, //
            8
            //
            )
        //
        );
  }
}
