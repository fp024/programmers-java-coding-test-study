package org.fp024.lv01;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam388351TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {700, 800, 1100}, //
            new int[][] {
              {710, 2359, 1050, 700, 650, 631, 659},
              {800, 801, 805, 800, 759, 810, 809},
              {1105, 1001, 1002, 600, 1059, 1001, 1100}
            }, //
            5,
            3
            //
            ),
        Arguments.of(
            new int[] {730, 855, 700, 720}, //
            new int[][] {
              {710, 700, 650, 735, 700, 931, 912},
              {908, 901, 805, 815, 800, 831, 835},
              {705, 701, 702, 705, 710, 710, 711},
              {707, 731, 859, 913, 934, 931, 905}
            }, //
            1,
            2
            //
            )
        //
        );
  }
}
