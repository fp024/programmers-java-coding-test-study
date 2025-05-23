package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * [PCCE 기출문제] 9번 / 이웃한 칸
 *   https://school.programmers.co.kr/learn/courses/30/lessons/250125
 */
@Slf4j
class Exam250125Tests {
  // ========== Target ==========
  static class Solution {
    public int solution(String[][] board, int h, int w) {
      int answer = 0;

      // 1. 정수를 저장할 변수 n을 만들고 board의 길이를 저장합니다. (주어진 보드는 항상 정사각형)
      int n = board.length;

      // 2. 같은 색으로 색칠된 칸의 개수를 저장할 변수 count를 만들고 0을 저장합니다.
      int count = 0;

      // 3. h와 w의 변화량을 저장할 정수 리스트 dw,dh를 만들고 각각 [0, 1, -1, 0], [1, 0, 0, -1]을 저장합니다.
      int[] dw = new int[] {0, 1, -1, 0};
      int[] dh = new int[] {1, 0, 0, -1};

      // 4. 반복문을 이용해 i 값을 0부터 3까지 1씩 증가시키며 아래 작업을 반복 합니다.
      for (int i = 0; i < dw.length; i++) {
        //  4-1. 체크할 칸의 h, w 좌표를 나타내는 변수 h_check, w_check를 만들고 각각 h + dh[i], w + dw[i]를 저장합니다.
        int h_check = h + dh[i];
        int w_check = w + dw[i];
        // 4-2. h_check가 0 이상 n 미만이고 w_check가 0 이상 n 미만이라면 다음을 수행합니다.
        if (h_check >= 0 && h_check < n && w_check >= 0 && w_check < n) {
          // 4-2-a. board[h][w]와 board[h_check][w_check]의 값이 동일하다면 count의 값을 1 증가시킵니다.
          if (board[h][w].equals(board[h_check][w_check])) { // NOSONAR
            count++;
          }
        }
      }

      // 5. count의 값을 return 합니다.
      answer = count;
      return answer;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam250125TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam250125TestData#extraDataProvider"
  })
  void testSolution(String[][] board, int h, int w, int expect) {
    assertThat(new Solution().solution(board, h, w)).isEqualTo(expect);
  }
  //
  // === 문제 읽고 첫 느낌 ===
  //   이 문제도 의사코드가 제공되는데, 그거 그대로 일단해보자 😅
  //   의사코드로 만들기만 해서 내긴 했는데, 당연히 통과다..😅
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
