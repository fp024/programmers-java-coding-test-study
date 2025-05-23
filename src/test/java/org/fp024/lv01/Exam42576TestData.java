package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

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
