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
  /** 문제 번호 */
  private static final int EXAM_NO = 160586;

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

          boolean isAllNotFound = true; // 모든 자판에서 검색이 한번도 안된 경우 확인
          for (int k = 0; k < keymap.length; k++) {
            int f = keymap[k].indexOf(c);

            if (f != -1) {
              count[k] = Math.min(count[k], f + 1);
              isAllNotFound = false;
            }
          }

          // 타겟 별로 눌렀는지 체크용도의 배열에서 최소값을 구하기 위해 정렬을 해줌
          Arrays.sort(count);

          if (isAllNotFound) {
            answer[i] = -1;
            break; // 💡특정 글자의 키입력을 모든 키맵에서 해결할 수 없는 상황에서는, 지금 것은 더이상 보지 않고 다른 타켓으로 넘어야함
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
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String[] keymap, String[] targets, int[] expect) {
    assertThat(new Solution().solution(keymap, targets)).isEqualTo(expect);
  }
  //
  // === 문제 읽고 첫 느낌 ===
  //   lv01 부터는 다 어려움 😓
  //   초💥~~ 어거지로 기본 테스트를 풀었다. 😓
  //   모든 자판(keymap)에서 해결할 수 없는 글자를 만났을 때는 실패로 처리하고 넘겨야하는데,
  //   그러지 않아서 실패를 했었다. 😅
  //   +2 점나왔다.
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   반복이 복잡해지는 건 다른 분들도 비슷하다. 😅
  //
}
