package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

@SuppressWarnings("unused") // JUnit MethodSource에서 리플렉션으로 접근하므로 사용되지 않는 것으로 오인될 수 있어 경고 억제
class Exam42576TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {"leo", "kiki", "eden"}, //
            new String[] {"eden", "kiki"},
            "leo"
            //
            )
        //

        ,
        Arguments.of(
            new String[] {"marina", "josipa", "nikola", "vinko", "filipa"}, //
            new String[] {"josipa", "filipa", "marina", "nikola"},
            "vinko"
            //
            ),
        Arguments.of(
            new String[] {"mislav", "stanko", "mislav", "ana"}, //
            new String[] {"stanko", "ana", "mislav"},
            "mislav"
            //
            )
        //
        );
  }

  static Stream<Arguments> extraDataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {"leo", "kiki", "kiki", "kiki", "eden"}, //
            new String[] {
              "kiki", "kiki", "eden", "leo",
            },
            "kiki"
            //
            )
        //
        );
  }
}
