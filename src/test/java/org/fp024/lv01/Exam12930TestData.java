package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam12930TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            "try hello world", //
            "TrY HeLlO WoRlD"
            //
            ),
        Arguments.of(
            "try      hello      world", //
            "TrY      HeLlO      WoRlD"
            //
            )
        //
        );
  }
}
