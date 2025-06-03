package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 택배 상자 꺼내기
 *   https://school.programmers.co.kr/learn/courses/30/lessons/389478
 */
@Slf4j
class Exam389478Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 389478;

  // ========== Target ==========
  static class Solution {
    public int solution(int n, int w, int num) {
      int answer = 0;

      // 내부 배열의 갯수 정의
      var rowCount = n / w + (n % w > 0 ? 1 : 0);

      var boxTable = new int[rowCount][w];

      var boxNumber = 1;
      // 일단은 박스를 지그재그로 채우진 말고 순서대로 채워넣어보기 😅
      for (int r = 0; r < rowCount; r++) {
        for (int c = 0; c < w; c++) {
          if (boxNumber <= n) {
            boxTable[r][c] = boxNumber++;
          }
        }
      }

      // 지그재그 모양을 만들기 위해서 특정 row를 역순 배열로 만들기 😅😅
      for (int r = 0; r < rowCount; r++) {
        if (r % 2 == 1) {
          for (int c = 0; c < w / 2; c++) {
            var temp = boxTable[r][c];
            boxTable[r][c] = boxTable[r][w - c - 1];
            boxTable[r][w - c - 1] = temp;
          }
        }
      }

      int targetRow = 0;
      int targetCol = 0;

      // 찾으려는 수의 row와 col 찾기 😂😂😂
      for (int r = 0; r < rowCount; r++) {
        for (int c = 0; c < w; c++) {
          if (boxTable[r][c] == num) {
            targetRow = r;
            targetCol = c;
          }
        }
      }

      // 찾으려는 상자의 위쪽으로 몇개가 있는지 알아보기 😅😅😅
      for (int r = targetRow; r < rowCount; r++) {
        if (boxTable[r][targetCol] > 0) {
          answer++;
        }
      }

      return answer;
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
  //   계산식으로 해야할 것 같지만..
  //   배열을 만들어서 숫자를 쌓아가도 될까?
  //   풀고도 좀 민망하긴하지만... 아주 원시적으로 풀은 것 같다. 😅
  //   +1점 받음..
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   첫번째 푸신분 수식으로서 해결하셨다. 👍👍👍
  //
}
