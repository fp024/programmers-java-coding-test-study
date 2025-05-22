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
  // ========== Target ==========
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
        if (lotto == 0) {
          zeroCount++;
          continue;
        }

        for (int winNum : win_nums) {
          if (lotto == winNum) {
            matchedCount++;
          }
        }
      }

      var answer = new int[2];
      answer[0] = Math.min(ALL_NUMBER_COUNT - matchedCount - zeroCount + 1, ALL_NUMBER_COUNT);
      answer[1] = Math.min(ALL_NUMBER_COUNT - matchedCount + 1, ALL_NUMBER_COUNT);

      return answer;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam77484TestData#defaultDataProvider", //
    "org.fp024.lv01.Exam77484TestData#extraDataProvider"
  })
  void testSolution(int[] lottos, int[] win_nums, int[] expect) {
    assertThat(new Solution().solution(lottos, win_nums)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   좀 복잡하게 생각해서 Set이나 List이런 걸 쓰면 편할까? 했는데,
  //   그냥 기본 기능으로만 쓰니까 더 잘 풀렸다. 👍
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   첫번째 Map<로또번호,일치여부>를 구성하신 분도 좋아보인다. 👍
  //   그런데 두번째... 등수를 스위치로 정의한 분이 읽기가 좋아보인다. 👍👍
  //
}
