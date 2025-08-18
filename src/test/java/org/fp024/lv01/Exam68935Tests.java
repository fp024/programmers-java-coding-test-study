package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 3진법 뒤집기
 *   https://school.programmers.co.kr/learn/courses/30/lessons/68935
 */
@Slf4j
class Exam68935Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 68935;

  // ========== Target ==========
  static class Solution {
    public int solution(int n) {
      // 3진법 수의 문자열
      String ternaryString = Integer.toString(n, 3);

      // 뒤집기
      StringBuilder sb = new StringBuilder(ternaryString);
      String reverseTernary = sb.reverse().toString();

      // 뒤집힌 3진법 문자열을 10진수로 표현해서 반환
      return Integer.parseInt(reverseTernary, 3);
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(int n, int expect) {
    assertThat(new Solution().solution(n)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  // ts에서는 3진법 계산을 했는데, Java에서는 진법계산이 내장되어있어서
  // 편하게 했다. 👍
  //
  // 💡ts 풀이
  // https://github.com/fp024/programmers-js-coding-test-study/blob/master/src/ts/lv_1/exam026-68935.ts
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
