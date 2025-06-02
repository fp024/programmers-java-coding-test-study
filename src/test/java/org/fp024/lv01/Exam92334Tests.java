package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 신고 결과 받기
 *   https://school.programmers.co.kr/learn/courses/30/lessons/92334
 */
@Slf4j
class Exam92334Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 92334;

  // ========== Target ==========
  static class Solution {
    /**
     * @param idList 유저 ID
     * @param report 유저가 신고한 ID
     * @param k 신고 한계 횟수
     * @return id별 차단 처리 완료 메일을 받은 횟수
     */
    public int[] solution(String[] idList, String[] report, int k) {
      int[] answer = new int[idList.length];

      // 불량 유저 별 신고자 Set
      Map<String, Set<String>> badUserAndReportersMap = new HashMap<>();

      // 신고자별 불량 유저 Set
      Map<String, Set<String>> reporterAndBadUsersMap = new HashMap<>();

      for (String reporterAndBadUser : report) {
        var reporterAndBadUserArray = reporterAndBadUser.split(" ");
        var reporter = reporterAndBadUserArray[0];
        var badUser = reporterAndBadUserArray[1];

        // 불량유저별 신고자들의 ID
        if (!badUserAndReportersMap.containsKey(badUser)) {
          Set<String> reporterSet = new HashSet<>();
          reporterSet.add(reporter);
          badUserAndReportersMap.put(badUser, reporterSet);
        } else {
          badUserAndReportersMap.get(badUser).add(reporter);
        }

        // 신고자별 불량 유저 Set
        if (!reporterAndBadUsersMap.containsKey(reporter)) {
          Set<String> badUserSet = new HashSet<>();
          badUserSet.add(badUser);
          reporterAndBadUsersMap.put(reporter, badUserSet);
        } else {
          reporterAndBadUsersMap.get(reporter).add(badUser);
        }
      }

      for (var i = 0; i < idList.length; i++) {
        var reporter = idList[i];
        for (var badUser : reporterAndBadUsersMap.getOrDefault(reporter, Set.of())) {
          answer[i] += badUserAndReportersMap.get(badUser).size() / k;
        }
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
  void testSolution(String[] idList, String[] report, int k, int[] expect) {
    assertThat(new Solution().solution(idList, report, k)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   쉽지가 않다. 💢
  //   코드가 꽤 드럽지만... 기본 테스트는 통과했는데...
  //   ...
  //   처음 한게 너무 에러가 많아서..
  //   불량 유저 별 신고자 Set을 따로 구성했다.
  //   어떤 유저가 몇번 신고 당했는지가 중요해서, 이렇게 하는게 맞는 것 같다.
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
