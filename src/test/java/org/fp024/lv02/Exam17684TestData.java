package org.fp024.lv02;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam17684TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            "KAKAO", //
            new int[] {11, 1, 27, 15}
            //
            ),
        Arguments.of(
            "TOBEORNOTTOBEORTOBEORNOT", //
            new int[] {20, 15, 2, 5, 15, 18, 14, 15, 20, 27, 29, 31, 36, 30, 32, 34}
            //
            ),
        Arguments.of(
            "ABABABABABABABAB", //
            new int[] {1, 2, 27, 29, 28, 31, 30}
            //
            ));
  }
}
