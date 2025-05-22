package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 대충 만든 자판
 *   https://school.programmers.co.kr/learn/courses/30/lessons/160586
 */
@Slf4j
class Exam160586Tests {
  // ========== Target ==========
  static class Solution {

    private static final int MAX_KEY_ELEMENT_SIZE = 100;

    public int[] solution(String[] keymap, String[] targets) {
      int[] answer = new int[targets.length];

      for (int i = 0; i < targets.length; i++) {

        // 타겟의 한글자씩
        for (int j = 0; j < targets[i].length(); j++) {

          char c = targets[i].charAt(j);

          // 타겟별로 몇번 눌렀는지 세는 배열
          int[] count = new int[keymap.length];

          Arrays.fill(count, MAX_KEY_ELEMENT_SIZE + 1);

          for (int k = 0; k < keymap.length; k++) {
            int f = keymap[k].indexOf(c);

            if (f != -1) {
              count[k] = Math.min(count[k], f + 1);
            }
          }

          // 타겟 별로 눌렀는지 체크용도의 배열에서 최소값을 구하기 위해 정렬을 해줌
          Arrays.sort(count);

          boolean isAllNotFound = true;

          // 검색이 한번도 안된 경우 확인
          for (int k : count) {
            if (k != MAX_KEY_ELEMENT_SIZE + 1) {
              isAllNotFound = false;
              break;
            }
          }

          if (isAllNotFound) {
            answer[i] = -1;
          } else {
            answer[i] += count[0];
          }
        }
      }

      return answer;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam160586TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam160586TestData#extraDataProvider"
  })
  void testSolution(String[] keymap, String[] targets, int[] expect) {
    assertThat(new Solution().solution(keymap, targets)).isEqualTo(expect);
  }
  //
  // === 문제 읽고 첫 느낌 ===
  //   lv01 부터는 다 어려움 😓
  //   초💥~~ 어거지로 기본 테스트를 풀었다. 😓
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
  //
}
