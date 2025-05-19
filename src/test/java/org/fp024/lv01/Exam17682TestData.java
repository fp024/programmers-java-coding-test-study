package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam17682TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            "1S2D*3T", //
            37
            //
            ),
        Arguments.of(
            "1D2S#10S", //
            9
            //
            ),
        Arguments.of(
            "1D2S0T", //
            3
            //
            ),
        Arguments.of(
            "1S*2T*3S", //
            23
            //
            ),
        Arguments.of(
            "1D#2S*3S", //
            5
            //
            ),
        Arguments.of(
            "1T2D3D#", //
            -4
            //
            ),
        Arguments.of(
            "1D2S3T*", //
            59
            //
            )
        //
        );
  }

  static Stream<Arguments> extraDataProvider() {
    return Stream.of(
        Arguments.of(
            "1S#2D#3T*", //
            45
            //
            )
        //
        );
  }
}
