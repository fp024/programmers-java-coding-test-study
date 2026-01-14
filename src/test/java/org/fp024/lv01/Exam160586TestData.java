package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
class Exam160586TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {"ABACD", "BCEFD"}, //
            new String[] {"ABCD", "AABB"}, //
            new int[] {9, 4}
            //
            ),
        //
        Arguments.of(
            new String[] {"AA"}, //
            new String[] {"B"}, //
            new int[] {-1}
            //
            ),
        //
        Arguments.of(
            new String[] {"AGZ", "BSSS"}, //
            new String[] {"ASA", "BGZ"}, //
            new int[] {4, 6}
            //
            )
        //
        );
  }

  static Stream<Arguments> extraDataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {
              "AA",
            }, //
            new String[] {"B", "A"}, //
            new int[] {-1, 1}
            //
            ),
        Arguments.of(
            new String[] {"ABACD", "BCEFD"}, //
            new String[] {"XABCD", "AABB"}, //
            new int[] {-1, 4}
            //
            )
        //
        );
  }
}
