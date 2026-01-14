package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
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

  static Stream<Arguments> extraDataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {"mumu", "soe", "poe"}, //
            new String[] {"poe", "poe"},
            new String[] {"poe", "mumu", "soe"}
            //
            )
        //
        );
  }
}
