package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 가장 많이 받은 선물
 *   https://school.programmers.co.kr/learn/courses/30/lessons/258712
 */
@Slf4j
class Exam258712Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 258712;

  // ========== Target ==========
  static class Solution {
    public int solution(final String[] friends, final String[] gifts) {
      var answer = 0;

      // 선물 주고 받은 이력 맵
      // <발신자ID, <수신자ID, 수신 횟수>>
      Map<String, Map<String, Integer>> senderAndReceiversHistory = new HashMap<>();

      // 선물지수 카운트 맵
      Map<String, Integer> giftScoreMap = new HashMap<>();
      for (var friend : friends) {
        giftScoreMap.put(friend, 0);
      }

      for (var g : gifts) {
        var giftHistory = g.split(" ");
        var sender = giftHistory[0];
        var receiver = giftHistory[1];

        var receiverCountMap = senderAndReceiversHistory.getOrDefault(sender, new HashMap<>());
        receiverCountMap.put(receiver, receiverCountMap.getOrDefault(receiver, 0) + 1);
        senderAndReceiversHistory.put(sender, receiverCountMap);
      }

      // 💡여기서부터 중요, 선물 주고받은 중복되지 않는 ID 쌍을 만들어야한다.
      for (var i = 0; i < friends.length; i++) {
        for (var j = i + 1; j < friends.length; j++) {
          final var friend1 = friends[i];
          final var friend2 = friends[j];

          // 두 사람이 선물을 주고 받은 기록 확인
          var friend1ToFriend2 =
              senderAndReceiversHistory.getOrDefault(friend1, Map.of()).getOrDefault(friend2, 0);
          var friend2ToFriend1 =
              senderAndReceiversHistory.getOrDefault(friend2, Map.of()).getOrDefault(friend1, 0);

          // 두 사람이 주고받은 선물 지수에 따라, 선물 지수 점수 증가
          if (friend1ToFriend2 > friend2ToFriend1) {
            giftScoreMap.put(friend1, giftScoreMap.get(friend1) + 1);
          } else if (friend1ToFriend2 < friend2ToFriend1) {
            giftScoreMap.put(friend2, giftScoreMap.get(friend2) + 1);
          } else {
            // 주고받은 기록이 하나도 없거나 같을 때는, 서로 주고받은 것 관계 없이 총 보내고 받은 선물수로 선물 지수 점수 산정
            var friend1TotalGiftScore =
                getTotalSendCount(friend1, senderAndReceiversHistory)
                    - getTotalReceiveCount(friend1, senderAndReceiversHistory);

            var friend2TotalGiftScore =
                getTotalSendCount(friend2, senderAndReceiversHistory)
                    - getTotalReceiveCount(friend2, senderAndReceiversHistory);

            if (friend1TotalGiftScore > friend2TotalGiftScore) {
              giftScoreMap.put(friend1, giftScoreMap.get(friend1) + 1);
            } else if (friend1TotalGiftScore < friend2TotalGiftScore) {
              giftScoreMap.put(friend2, giftScoreMap.get(friend2) + 1);
            }
          }
        }
      }

      for (String id : giftScoreMap.keySet()) {
        answer = Math.max(answer, giftScoreMap.get(id));
      }

      return answer;
    }

    /** 특정 ID가 보낸 총 선물 갯수 */
    static int getTotalSendCount(
        String id, Map<String, Map<String, Integer>> senderAndReceiversHistory) {
      int total = 0;
      Map<String, Integer> sendHistory = senderAndReceiversHistory.get(id);
      if (sendHistory == null) {
        return 0;
      }
      for (var receiveHistory : sendHistory.entrySet()) {
        total += receiveHistory.getValue();
      }
      return total;
    }

    /** 특정 ID가 받은 총 선물 갯수 */
    static int getTotalReceiveCount(
        String id, Map<String, Map<String, Integer>> senderAndReceiversHistory) {
      int total = 0;

      for (var receiveHistory : senderAndReceiversHistory.entrySet()) {
        if (receiveHistory.getValue().containsKey(id)) {
          total += receiveHistory.getValue().get(id);
        }
      }
      return total;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String[] friends, String[] gifts, int expect) {
    assertThat(new Solution().solution(friends, gifts)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   일단 먼저 C++로 풀어봐서, 풀이로직은 거의 같다고 보면 될 것 같다.
  //   https://github.com/fp024/programmers-c-coding-test-study/blob/master/lv01/Exam001_258712.cpp
  //   단지... 총 선물 보낸수와, 받은 수를 메서드로 별도 계산하게 한 것 말고는 크게 차이가 없다. 😅
  //
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
