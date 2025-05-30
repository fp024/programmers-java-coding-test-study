package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

      Map<String, Player> playerMap = new HashMap<>();
      List<Player> playerRankList = new ArrayList<>();

      // 선수 이름으로 선수를 빠르게 검색할 수 있는 Map과 랭크대로 선수가 들어있는 리스트 생성
      for (int i = 0; i < players.length; i++) {
        Player player = new Player(players[i], i + 1);
        playerMap.put(players[i], player);
        playerRankList.add(player);
      }

      // 랭크 UP
      for (var name : callings) {
        var player = playerMap.get(name);
        var prevPlayer = playerRankList.get(player.rank - 2);
        if (prevPlayer != null) {
          player.rank--;
          prevPlayer.rank++;
          playerRankList.set(player.rank - 1, player);
          playerRankList.set(prevPlayer.rank - 1, prevPlayer);
        }
      }

      return playerRankList.stream().map(p -> p.name).toArray(String[]::new);
    }

    static class Player {
      final String name;
      int rank;

      public Player(String name, int rank) {
        this.name = name;
        this.rank = rank;
      }

      @Override
      public String toString() {
        return "{name:'" + name + "', rank:" + rank + "}";
      }

      @Override
      public int hashCode() {
        return name.hashCode();
      }
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
  //   뭔가 이름 검색할 때, 그냥 반복문으로 검색하면 시간 초과 뜰 것 같은데... 😂
  //   Player에대가 prevPlayer, nextPlayer를 정의해서 하려다가 더 복잡해지고 풀리지도 않아서,
  //   List의 인덱스 번호를 활용하고 Map으로는 Player 이름을 빠르게 검색하도록 바꿔봤다.
  //   통과했고 점수는 +3점 받음 😊
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   이해가 잘 안되서 일단 Player를 만들어놓고 했었는데, 다른 분들보니, Map만으로도 잘 해결 하셨다. 👍👍
  //
}
