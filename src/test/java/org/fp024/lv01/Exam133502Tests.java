package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 햄버거 만들기
 *   https://school.programmers.co.kr/learn/courses/30/lessons/133502
 */
@Slf4j
class Exam133502Tests {
  // ========== Target ==========
  static class Solution {
    public int solution(int[] ingredient) {
      int answer = 0;

      StringBuilder sb = new StringBuilder();

      for (int j : ingredient) {
        sb.append(j);
      }

      int foundIdx = 0;
      while (true) {
        int hamburgerIndex = sb.indexOf("1231", foundIdx);

        if (hamburgerIndex > -1) {
          sb.delete(hamburgerIndex, hamburgerIndex + 4);
          foundIdx = Math.max(hamburgerIndex - 2, 0);
          answer++;
        } else {
          break;
        }
      }

      return answer;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam133502TestData#defaultDataProvider", //
    "org.fp024.lv01.Exam133502TestData#extraDataProvider"
  })
  void testSolution(int[] ingredient, int expect) {
    assertThat(new Solution().solution(ingredient)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //  문자열로 변경해서 햄버거(1231)를 찾은 만큼 줄여가면서 해결을 하긴 했는데.. 어떨지 ...
  //
  //  indexOf에서 문자열을 검색하는 범위를 줄여서 해결을 했다. +2점을 받음. 😊
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   그런데.. 다른 분들 첫 풀이가 좋은 느낌이다. 스택을 활용해서 푸신 것 같음. 👍
  //
}
