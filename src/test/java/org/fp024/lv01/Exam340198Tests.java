package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * [PCCE 기출문제] 10번 / 공원
 *   https://school.programmers.co.kr/learn/courses/30/lessons/340198
 */
@Slf4j
class Exam340198Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 340198;

  // ========== Target ==========
  static class Solution {
    public int solution(int[] mats, String[][] park) {
      int answer = -1;
      int maxSquare = 0;

      // (1) 동적프로그래밍(DP)를 활용가능하도록 빈 공간을 1, 돗자리들이 차지한 영역을 0으로 바꾼다.
      int[][] dp = new int[park.length][park[0].length];
      for (int i = 0; i < park.length; i++) {
        for (int j = 0; j < park[i].length; j++) {
          dp[i][j] = park[i][j].equals("-1") ? 1 : 0;
        }
      }

      // (2) 정사각형이 가능한 조건을 하나씩 채워나간다.
      //     현재 위치에서 좌/상/좌상과 각각 더했을 때 최소값이 현재 위치에서 가능한 정사각형 길이
      for (int i = 1; i < dp.length; i++) {
        for (int j = 1; j < dp[i].length; j++) {
          // 💡현재 위치가 정사각형에 포함될 수 있는지 확인이 되야하므로, 1인지 검사해야한다.
          if (dp[i][j] == 1) {
            int up = dp[i - 1][j];
            int left = dp[i][j - 1];
            int upLeft = dp[i - 1][j - 1];
            dp[i][j] += Math.min(Math.min(up, left), upLeft);
          }
        }
      }

      // (3) 최대 정사각형 한변 얻기
      for (int i = 0; i < dp.length; i++) {
        for (int j = 0; j < dp[i].length; j++) {
          System.out.print(dp[i][j] + " ");
          maxSquare = Math.max(maxSquare, dp[i][j]);
        }
        System.out.println();
      }

      Arrays.sort(mats);
      for (int i = mats.length - 1; i >= 0; i--) {
        if (mats[i] <= maxSquare) {
          answer = mats[i];
          break;
        }
      }

      return answer;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    // "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(int[] mats, String[][] park, int expect) {
    assertThat(new Solution().solution(mats, park)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   처음에 어떻게 해결해야할지 막막해서 코딩 테스트 책을 보았는데,
  //   "가장 큰 정사각형 찾기" 풀이 해설을 보니, 뭔가 이해가 된다.
  //   - https://school.programmers.co.kr/learn/courses/30/lessons/12905
  //   지금 문제를 위 문제와 비슷하게 풀면 쉽게 해결 될 것 같다.
  //   🤔 기본 테스트는 통과하는데, 제출시 다량 실패한다. 왜그럴까? 😂😂😂
  //
  //   dp 배열을 구성할 때.. 현재 위치가 1임을 확인하지 않아서, 오류가 있었다. 😂
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   바로 이해는 안되지만.. 모두들 자신만의 스타일이 있는 것 같다. 👍👍
  //
}
