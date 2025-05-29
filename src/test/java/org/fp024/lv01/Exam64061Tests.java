package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 크레인 인형뽑기 게임
 *   https://school.programmers.co.kr/learn/courses/30/lessons/64061
 */
@Slf4j
class Exam64061Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 64061;

  // ========== Target ==========
  static class Solution {
    public int solution(int[][] board, int[] moves) {
      int pullCount = 0;
      List<Integer> result = new ArrayList<>();

      //
      for (int m : moves) {
        int dollNumber = getDoll(board, m);
        if (dollNumber != 0) {
          pullCount++;
          // 💡 result.get(result.size() - 1)는 Java 21에서 result.getLast() 로 바꿀 수 있다.
          if (!result.isEmpty() && result.get(result.size() - 1) == dollNumber) {
            // 💡 Java 21에서 result.removeLast()로 바꿀 수 있다.
            result.remove(result.size() - 1);
          } else {
            result.add(dollNumber);
          }
        }
      }

      return pullCount - result.size();
    }

    /** 특정 col의 인형 번호 가져오기 */
    int getDoll(int[][] board, int move) {
      int dollNumber = 0;
      for (int i = 0; i < board.length; i++) {
        dollNumber = board[i][move - 1];

        if (dollNumber != 0) {
          board[i][move - 1] = 0;
          return dollNumber;
        }
      }
      return dollNumber;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(int[][] board, int[] moves, int expect) {
    assertThat(new Solution().solution(board, moves)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   일단 List에 넣을 때. 앞에 중복이 있으면 제거하고 입력하지 않는 방법으로
  //   뽑은 주머니를 완성하고,
  //   "인형을 성공적으로 뽑은 카운트" - "뽑은 주머니의 인형 갯수"로 사라진 인형 갯수를 구했다.
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   다른 분들보면 스택을 주소 쓰셨다.
  //
}
