package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
class Exam258712TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {"muzi", "ryan", "frodo", "neo"}, //
            new String[] {
              "muzi frodo", //
              "muzi frodo",
              "ryan muzi",
              "ryan muzi",
              "ryan muzi",
              "frodo muzi",
              "frodo ryan",
              "neo muzi"
            },
            2
            //
            ),
        Arguments.of(
            new String[] {"joy", "brad", "alessandro", "conan", "david"}, //
            new String[] {
              "alessandro brad", //
              "alessandro joy",
              "alessandro conan",
              "david alessandro",
              "alessandro david"
            },
            4
            //
            ),
        Arguments.of(
            new String[] {"a", "b", "c"}, //
            new String[] {
              "a b", //
              "b a", //
              "c a", //
              "a c", //
              "a c", //
              "c a"
            },
            0
            //
            )
        //
        );
  }
}
