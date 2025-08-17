package org.fp024.lv02;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

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
