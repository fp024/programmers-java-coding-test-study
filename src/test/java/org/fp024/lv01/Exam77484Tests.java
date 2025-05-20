package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 로또의 최고 순위와 최저 순위
 *   https://school.programmers.co.kr/learn/courses/30/lessons/77484
 */
@Slf4j
class Exam77484Tests {
  static class Solution {
    static final int ALL_NUMBER_COUNT = 6;

    /**
     * @param lottos 로또 번호
     * @param win_nums 당첨 번호
     * @return 가능한 최고 등수 최저 등수
     */
    public int[] solution(int[] lottos, int[] win_nums) {

      int zeroCount = 0;
      int matchedCount = 0;

      for (int lotto : lottos) {
        for (int winNum : win_nums) {
          if (lotto == winNum) {
            matchedCount++;
          }
        }

        if (lotto == 0) {
          zeroCount++;
        }
      }

      var answer = new int[2];
      answer[0] = ALL_NUMBER_COUNT - matchedCount - zeroCount + 1;
      if (zeroCount == ALL_NUMBER_COUNT) {
        answer[1] = ALL_NUMBER_COUNT - matchedCount;
      } else {
        answer[1] = ALL_NUMBER_COUNT - matchedCount + 1;
      }

      return answer;
    }
  }

  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam77484TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam77484TestData#extraDataProvider"
  })
  void testSolution(int[] lottos, int[] win_nums, int[] expect) {
    assertThat(new Solution().solution(lottos, win_nums)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  // ...
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
