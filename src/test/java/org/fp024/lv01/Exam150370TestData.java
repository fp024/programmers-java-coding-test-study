package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
class Exam150370TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            "2022.05.19", //
            new String[] {
              "A 6", //
              "B 12", //
              "C 3"
            },
            new String[] {
              "2021.05.02 A", //
              "2021.07.01 B",
              "2022.02.19 C",
              "2022.02.20 C"
            },
            new int[] {1, 3}
            //
            ),
        Arguments.of(
            "2020.01.01", //
            new String[] {
              "Z 3", //
              "D 5"
            },
            new String[] {
              "2019.01.01 D", //
              "2019.11.15 Z",
              "2019.08.02 D",
              "2019.07.01 D",
              "2018.12.28 Z"
            },
            new int[] {1, 4, 5}
            //
            )
        //
        );
  }

  static Stream<Arguments> extraDataProvider() {
    return Stream.of(
        Arguments.of(
            "0000.02.01", //
            new String[] {
              "A 1", //
            },
            new String[] {
              "0000.01.01 A", //
            },
            new int[] {1}
            //
            ));
  }
}
