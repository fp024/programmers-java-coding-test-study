package org.fp024.lv02;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
class Exam60057TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            "aabbaccc", //
            7
            //
            ),
        Arguments.of(
            "ababcdcdababcdcd", //
            9
            //
            ),
        Arguments.of(
            "abcabcdede", //
            8
            //
            ),
        Arguments.of(
            "abcabcabcabcdededededede", //
            14
            //
            ),
        Arguments.of(
            "xababcdcdababcdcd", //
            17
            //
            )

        //
        );
  }
}
