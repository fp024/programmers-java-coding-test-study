package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 카드 뭉치
 *   https://school.programmers.co.kr/learn/courses/30/lessons/159994
 */
@Slf4j
class Exam159994Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 159994;

  // ========== Target ==========
  static class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
      int cards1Idx = 0;
      int cards2Idx = 0;
      for (String g : goal) {
        if (cards1Idx < cards1.length && cards1[cards1Idx].equals(g)) {
          cards1Idx++;
        } else if (cards2Idx < cards2.length && cards2[cards2Idx].equals(g)) {
          cards2Idx++;
        }
      }

      return cards1Idx + cards2Idx == goal.length ? "Yes" : "No";
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String[] cards1, String[] cards2, String[] goal, String expect) {
    assertThat(new Solution().solution(cards1, cards2, goal)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  // 특별한 자료구조가 필요없을 것 같아서 그냥 진행해보다가...
  // 뭔가 전보다 단순하게 풀었다고 생각했는데, 좋아요 1등 풀이와 로직이 같아졌다. 😊
  //
  // * TS:
  // https://github.com/fp024/programmers-js-coding-test-study/blob/master/src/ts/lv_1/exam039-159994.ts
  // * Python:
  // https://github.com/fp024/programmers-python-coding-test-study/blob/master/src/lv01/exam003_159994.py
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
