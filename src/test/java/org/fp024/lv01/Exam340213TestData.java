package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam340213TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            "34:33", //
            "13:00",
            "00:55",
            "02:55",
            new String[] {"next", "prev"},
            "13:00"
            //
            ),
        Arguments.of(
            "10:55", //
            "00:05",
            "00:15",
            "06:55",
            new String[] {"prev", "next", "next"},
            "06:55"
            //
            ),
        Arguments.of(
            "07:22", //
            "04:05",
            "00:15",
            "04:07",
            new String[] {"next"},
            "04:17"
            //
            ));
  }
}
