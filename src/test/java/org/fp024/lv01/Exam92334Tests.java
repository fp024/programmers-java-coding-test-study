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

      // 신고자, 유저가 신고한 ID
      Map<String, Set<String>> reporterAndBadUsersMap = new HashMap<>();
      // 불량유저_신고자 Set
      Set<String> badUserAndReporterSet = new HashSet<>();

      for (String reporterAndBadUser : report) {
        var reporterAndBadUserArray = reporterAndBadUser.split(" ");
        var reporter = reporterAndBadUserArray[0];
        var badUser = reporterAndBadUserArray[1];

        // 유저별 신고자 ID 카운트
        if (!reporterAndBadUsersMap.containsKey(reporter)) {
          Set<String> badUserSet = new HashSet<>();
          badUserSet.add(badUser);
          reporterAndBadUsersMap.put(reporter, badUserSet);
        } else {
          reporterAndBadUsersMap.get(reporter).add(badUser);
        }

        // 불량유저_신고자 Set 구성
        badUserAndReporterSet.add(badUser + "_" + reporter);
      }

      for (int i = 0; i < idList.length; i++) {
        Set<String> badUsers = reporterAndBadUsersMap.getOrDefault(idList[i], Set.of());
        for (String badUser : badUsers) {
          for (var itr : badUserAndReporterSet) {
            if (itr.startsWith(badUser + "_")) {
              answer[i]++;
            }
          }
        }
      }

      for (int i = 0; i < answer.length; i++) {
        answer[i] = answer[i] / k;
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
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
