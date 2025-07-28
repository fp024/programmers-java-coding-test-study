package org.fp024.lv02;

import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam42584TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {1, 2, 3, 2, 3}, //
            new int[] {4, 3, 1, 1, 0}
            //
            ),
        Arguments.of(
            new int[] {5, 4, 3, 2, 1}, //
            new int[] {1, 1, 1, 1, 0}
            //
            ),
        Arguments.of(
            new int[] {6, 9, 5, 7}, //
            new int[] {2, 1, 1, 0}
            //
            ));
  }

  // 💡최악의 경우 테스트: 주식이 떨어지는 경우가 없는 전체 오름차순 배열일 경우.
  private static final int LARGE_DATA_COUNT = 100_000;

  static Stream<Arguments> extraDataProvider() {
    return Stream.of(
        Arguments.of(
            IntStream.rangeClosed(1, LARGE_DATA_COUNT).toArray(), //
            IntStream.iterate(LARGE_DATA_COUNT - 1, i -> i >= 0, i -> i - 1).toArray()
            //
            )
        //
        );
  }
}
