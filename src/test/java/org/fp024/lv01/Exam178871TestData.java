package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam178871TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {"mumu", "soe", "poe", "kai", "mine"}, //
            new String[] {"kai", "kai", "mine", "mine"},
            new String[] {"mumu", "kai", "mine", "soe", "poe"}
            //
            )
        //
        );
  }
}
