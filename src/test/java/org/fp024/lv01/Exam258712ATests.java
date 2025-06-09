package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 가장 많이 받은 선물 - AI 코드 리뷰
 *   https://school.programmers.co.kr/learn/courses/30/lessons/258712
 */
@Slf4j
class Exam258712ATests {
  /** 문제 번호 */
  private static final int EXAM_NO = 258712;

  // ========== Target ==========
  static class Solution {
    public int solution(final String[] friends, final String[] gifts) {
      var answer = 0;

      // 선물 주고 받은 이력 맵
      // <발신자ID, <수신자ID, 수신 횟수>>
      Map<String, Map<String, Integer>> senderAndReceiversHistory = new HashMap<>();

      // 선물 지수
      Map<String, Integer> giftPointHistory = new HashMap<>();
      // 다음달에 받을 선물 갯수
      Map<String, Integer> nextMonthGiftCount = new HashMap<>();
      for (var friend : friends) {
        giftPointHistory.put(friend, 0);
        nextMonthGiftCount.put(friend, 0);
      }

      for (var g : gifts) {
        var giftHistory = g.split(" ");
        var sender = giftHistory[0];
        var receiver = giftHistory[1];

        var receiverCountMap = senderAndReceiversHistory.getOrDefault(sender, new HashMap<>());
        receiverCountMap.put(receiver, receiverCountMap.getOrDefault(receiver, 0) + 1);
        senderAndReceiversHistory.put(sender, receiverCountMap);

        giftPointHistory.put(sender, giftPointHistory.getOrDefault(sender, 0) + 1);
        giftPointHistory.put(receiver, giftPointHistory.getOrDefault(receiver, 0) - 1);
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

          // 두 사람이 주고받은 선물 지수에 따라, 다음달에 받을 선물 수 증가
          if (friend1ToFriend2 > friend2ToFriend1) {
            nextMonthGiftCount.put(friend1, nextMonthGiftCount.get(friend1) + 1);
          } else if (friend1ToFriend2 < friend2ToFriend1) {
            nextMonthGiftCount.put(friend2, nextMonthGiftCount.get(friend2) + 1);
          } else {
            // 주고받은 기록이 하나도 없거나 같을 때는, 서로 주고받은 것 관계 없이 총 보내고 받은 선물수로 다음달에 받을 선물 수 산정
            var friend1TotalGiftScore = giftPointHistory.get(friend1);
            var friend2TotalGiftScore = giftPointHistory.get(friend2);
            if (friend1TotalGiftScore > friend2TotalGiftScore) {
              nextMonthGiftCount.put(friend1, nextMonthGiftCount.get(friend1) + 1);
            } else if (friend1TotalGiftScore < friend2TotalGiftScore) {
              nextMonthGiftCount.put(friend2, nextMonthGiftCount.get(friend2) + 1);
            }
          }
        }
      }

      for (String id : nextMonthGiftCount.keySet()) {
        answer = Math.max(answer, nextMonthGiftCount.get(id));
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
  void testSolution(String[] friends, String[] gifts, int expect) {
    assertThat(new Solution().solution(friends, gifts)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   AI가 굳이 선물지수 계산을 위해... 총선물 받은수, 총 선물 보낸수를 구하는 메서드를 따로 만들 필요없이,
  //   senderAndReceiversHistory 맵을 구성할 때.. 바로 계산할 수 있어서, 거기에 포함했다.
  //
  //   그런데 데이터 클래스 분리와 메서드 분리등을 AI가 추천해줬는데, 코딩 테스트 코드이고,
  //   너무 분리하면 오히려 나중에 다시 볼 때 해깔릴 것 같다고 했더니. AI도 이해했음. 😅
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
