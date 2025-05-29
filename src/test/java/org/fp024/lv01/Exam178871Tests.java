package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 달리기 경주
 *   https://school.programmers.co.kr/learn/courses/30/lessons/178871
 */
@Slf4j
class Exam178871Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 178871;

  // ========== Target ==========
  static class Solution {
    public String[] solution(String[] players, String[] callings) {
      // 이름 호명 최대 100만번 호출 😂
      for (var name : callings) {
        var foundIndex = -1;
        // 검색은 최대 5만, 아마도 이 반복이 누적되서 시간초과 뜰 것 같은데... 😂
        for (int i = 0; i < players.length; i++) {
          if (players[i].equals(name)) {
            foundIndex = i;
            break;
          }
        }

        if (foundIndex > 0) {
          var temp = players[foundIndex - 1];
          players[foundIndex - 1] = name;
          players[foundIndex] = temp;
        }
      }

      return players;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String[] players, String[] callings, String[] expect) {
    assertThat(new Solution().solution(players, callings)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   뭔가 이름 검색할 때, 그냥 반복문으로 검색하면 시간 초과 뜰 것 같은데... 😂
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
