package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 달리기 경주 - 불필요한 class, 자료구조 제거
 *   https://school.programmers.co.kr/learn/courses/30/lessons/178871
 */
@Slf4j
class Exam178871ATests {
  /** 문제 번호 */
  private static final int EXAM_NO = 178871;

  // ========== Target ==========
  static class Solution {
    public String[] solution(String[] players, String[] callings) {

      // <이름, 순위> 맵
      Map<String, Integer> playerMap = new HashMap<>();

      // 선수 이름으로 순위를 빠르게 검색할 수 있는 Map 생성
      // Map 저장되는 내용을 배열의 index와 동일하게 동기화하게 되면은 (1등을 rank = 0)
      // 순위 배열(players)에서 순위를 인덱스로 사용해서 바로 접근할 수 있다.
      for (int i = 0; i < players.length; i++) {
        playerMap.put(players[i], i);
      }

      for (var name : callings) {
        var rank = playerMap.get(name);
        var prevRank = rank - 1;
        var prevRankName = players[prevRank];

        playerMap.put(prevRankName, rank);
        playerMap.put(name, prevRank);

        players[prevRank] = name;
        players[rank] = prevRankName;
      }

      return players;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String[] players, String[] callings, String[] expect) {
    assertThat(new Solution().solution(players, callings)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   최초로 푼 내용에서 불필요한 부분을 제거하자.
  //   랭크를 List로 새로 정의할 필요가 없고, Player도 별도 클래스를 만들 필요는 없었음.
  //
  //   결국 더 간단하게 잘 풀렸다. 😅
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
