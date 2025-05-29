package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 완주하지 못한 선수
 *   https://school.programmers.co.kr/learn/courses/30/lessons/42576
 */
@Slf4j
class Exam42576Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 42576;

  // ========== Target ==========
  static class Solution {
    public String solution(String[] participant, String[] completion) {
      String answer = "";

      Map<String, Integer> participantMap = new HashMap<>();

      for (String p : participant) {
        Integer count = participantMap.get(p);
        if (count == null) {
          participantMap.put(p, 1);
        } else {
          participantMap.put(p, count + 1);
        }
      }

      for (String c : completion) {
        Integer count = participantMap.get(c);

        if (count == 1) {
          participantMap.remove(c);
          continue;
        }

        participantMap.put(c, count - 1);
      }

      // 문제 조건에 따라서 반드시 Map에 한명이 남는다.
      answer = participantMap.keySet().iterator().next();

      return answer;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String[] participant, String[] completion, String expect) {
    assertThat(new Solution().solution(participant, completion)).isEqualTo(expect);
  }
  //
  // === 문제 읽고 첫 느낌 ===
  //   금방 넘어갈 것 같았는데, 은근이 힘들다.
  //   일단 동명이인이 있다고 해서, Map<참가자이름, 카운트>로 처리했다.
  //   +1점 얻었다. 😅
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   모두들 비슷비슷하게 풀은 것 같다.
  //   map의 getOrDefault() 메서드를 사용했으면 첫번째 for문이 좀 더 짧아 졌을 것 같다.
  //   그리고 아예 자료구조를 구현하신 분도 있음 (3번째 풀이) 👍👍👍
  //
}
