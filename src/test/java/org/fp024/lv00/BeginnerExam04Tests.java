package org.fp024.lv00;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 코딩 테스트 입문 04
 * https://school.programmers.co.kr/learn/challenges/beginner?order=acceptance_desc
 *
 * 3개 문제 남긴 했는데... 점점 어려워져서 일찍 클래스를 분리했다. 😂
 */
@Slf4j
class BeginnerExam04Tests {

  /*
   * 💡💡 겹치는 선분의 길이
   *     https://school.programmers.co.kr/learn/courses/30/lessons/120876
   *
   * 힌트: https://school.programmers.co.kr/questions/42873
   *
   */
  @ParameterizedTest
  @MethodSource("test_120876_dataProvider")
  void test_120876(int[][] lines, int expect) {
    final int BASE = 200;
    int answer = 0;

    int[] markerArray = new int[BASE];

    // 3개의 선분 선분입력은 3개로 고정됨
    for (int i = 0; i < 3; i++) {

      int start = lines[i][0] + BASE / 2;
      int end = lines[i][1] + BASE / 2;

      if (start > end) {
        int temp = start;
        start = end;
        end = temp;
      }

      for (int j = start; j < end; j++) {
        markerArray[j]++;
      }
    }

    for (int i = 0; i < BASE; i++) {
      System.out.print(markerArray[i]);
      if (markerArray[i] > 1) {
        answer++;
      }
      if ((i + 1) % 10 == 0) {
        System.out.println();
      }
    }
    System.out.println();
    System.out.println();

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120876_dataProvider() {
    return Stream.of(
        Arguments.of(
            new int[][] {
              {0, 1}, //
              {2, 5}, //
              {3, 9}
            }, //
            2
            //
            ),
        Arguments.of(
            new int[][] {
              {-1, 1}, //
              {1, 3}, //
              {3, 9}
            }, //
            0
            //
            ),
        Arguments.of(
            new int[][] {
              {0, 5}, //
              {3, 9}, //
              {1, 10}
            }, //
            8
            //
            ),
        Arguments.of(
            new int[][] {
              {-100, -99}, //
              {-100, -98}, //
              {0, 10}
            }, //
            1
            //
            )
        //

        );
  }
}
