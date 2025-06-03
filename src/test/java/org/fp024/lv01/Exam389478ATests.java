package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 택배 상자 꺼내기 - AI는 수학적으로 타겟 좌표를 즉시찾아 해결 👍
 *   https://school.programmers.co.kr/learn/courses/30/lessons/389478
 */
@Slf4j
class Exam389478ATests {
  /** 문제 번호 */
  private static final int EXAM_NO = 389478;

  // ========== Target ==========
  static class Solution {
    public int solution(int n, int w, int num) {
      // 수학적 접근으로 최적화 가능
      int row = (num - 1) / w; // 0-based row index
      int col = (num - 1) % w; // 0-based col index

      // 홀수 행(1, 3, 5...)은 지그재그 때문에 열 순서가 바뀜
      if (row % 2 == 1) {
        col = w - 1 - col;
      }

      // 해당 열에서 타겟 상자부터 맨 위까지의 상자 개수
      int totalRows = (n + w - 1) / w; // ceiling division
      return totalRows - row;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(int n, int w, int num, int expect) {
    assertThat(new Solution().solution(n, w, num)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   ...
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   AI에게 내가 먼저 통과한 코드(Exam389478Tests)의 개선점을 물어봤을 때,
  //   수식으로 한번에 좌표를 찾아냈다. 그런데.. 여전히 잘 모르겠음. 😅
  //
}
