package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * [PCCE 기출문제] 9번 / 지폐 접기 - 340199
 *   https://school.programmers.co.kr/learn/courses/30/lessons/340199
 */
@Slf4j
class Exam340199Tests {
  static class Solution {
    /**
     * @param wallet 지갑 가로,세로 길이
     * @param bill 지폐 가로,세로 길이
     * @return 지폐를 접은 횟수
     */
    public int solution(int[] wallet, int[] bill) {
      int answer = 0;

      int shortBill = Math.min(bill[0], bill[1]);
      int shortWallet = Math.min(wallet[0], wallet[1]);
      int longBill = Math.max(bill[0], bill[1]);
      int longWallet = Math.max(wallet[0], wallet[1]);

      while (shortBill > shortWallet || longBill > longWallet) {
        if (bill[0] > bill[1]) {
          bill[0] = bill[0] / 2;
        } else {
          bill[1] = bill[1] / 2;
        }
        answer++;

        shortBill = Math.min(bill[0], bill[1]);
        shortWallet = Math.min(wallet[0], wallet[1]);
        longBill = Math.max(bill[0], bill[1]);
        longWallet = Math.max(wallet[0], wallet[1]);
      }

      return answer;
    }
  }

  @ParameterizedTest
  @MethodSource("defaultDataProvider")
  void testSolution(int[] wallet, int[] bill, int expect) {
    assertThat(new Solution().solution(wallet, bill)).isEqualTo(expect);
  }

  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {30, 15}, //
            new int[] {26, 27},
            1
            //
            ),
        Arguments.of(
            new int[] {50, 50}, //
            new int[] {100, 241},
            4
            //
            )
        //
        );
  }
  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   문제에 있던 의사코드 그대로 풀었다.
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   길이 2 배열의 max나 min 계산하는 부분을 나도 좀 함수로 뺄 것 그랬다.
  //
}
