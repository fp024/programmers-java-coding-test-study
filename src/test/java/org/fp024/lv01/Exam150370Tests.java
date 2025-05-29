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
 * 개인정보 수집 유효기간
 *   https://school.programmers.co.kr/learn/courses/30/lessons/150370
 */
@Slf4j
class Exam150370Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 150370;

  // ========== Target ==========
  static class Solution {

    private static final int DAYS_IN_MONTH = 28;

    /** 28일 기준으로 현재 일자의 일수를 반환하는 함수 */
    int calculateTotalDays(String yyyyMMdd) {
      var dateArray = yyyyMMdd.split("\\.");
      // 💡yyyy영역에 0000년이 들어올 것 같진 않지만.. 방어 목적으로 넣어두자!
      var year = Math.max((Integer.parseInt(dateArray[0]) - 1) * 12 * DAYS_IN_MONTH, 0);
      var month = (Integer.parseInt(dateArray[1]) - 1) * DAYS_IN_MONTH;
      var day = Integer.parseInt(dateArray[2]);

      return year + month + day;
    }

    public int[] solution(String today, String[] terms, String[] privacies) {
      List<Integer> answer = new ArrayList<>();

      int todayInDays = calculateTotalDays(today);

      // 약관 코드 맵: <코드, 유효일수(일)>
      Map<String, Integer> termsMap = new HashMap<>();
      for (String term : terms) {
        var termArray = term.split(" ");
        var termCode = termArray[0];
        var termPeriodInDays = Integer.parseInt(termArray[1]) * DAYS_IN_MONTH;
        termsMap.put(termCode, termPeriodInDays);
      }

      for (int i = 0; i < privacies.length; i++) {
        var privacyArray = privacies[i].split(" ");
        var privacyStartDays = calculateTotalDays(privacyArray[0]);
        var termType = privacyArray[1];

        // 💡 개인정보 수집일 + 약관일수는 유효기간이 끝나는 다음날을 가리키므로, -1을 빼서 정확한 만료일과 비교한다.
        //    (약관 동의 기간에 수집 일이 포함된다.)
        if (privacyStartDays + termsMap.get(termType) - 1 < todayInDays) {
          answer.add(i + 1);
        }
      }

      return answer.stream().mapToInt(Integer::intValue).toArray();
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String today, String[] terms, String[] privacies, int[] expect) {
    assertThat(new Solution().solution(today, terms, privacies)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   처음에 LocalDate를 사용해서 실제 날짜로 파싱해서 사용해야하나 했는데,
  //   모든 달이 28일이라고 해서, 28일 기준으로 일수를 반환하는 메서드를 만들어서 적용했다.
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   첫번째 풀이하신 분도 일수로 바꿔서 푸셨다. 👍
  //
}
