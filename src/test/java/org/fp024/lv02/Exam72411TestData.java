package org.fp024.lv02;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam72411TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            // orders
            new String[] {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"}, //
            // course
            new int[] {2, 3, 4},
            //
            new String[] {"AC", "ACDE", "BCFG", "CDE"}
            //
            ),
        Arguments.of(
            new String[] {"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"}, //
            new int[] {2, 3, 5},
            new String[] {"ACD", "AD", "ADE", "CD", "XYZ"}
            //
            ),
        Arguments.of(
            new String[] {"XYZ", "XWY", "WXA"}, //
            new int[] {2, 3, 4},
            new String[] {"WX", "XY"}
            //
            ));
  }
}
