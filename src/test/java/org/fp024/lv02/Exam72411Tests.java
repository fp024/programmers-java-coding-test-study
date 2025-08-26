package org.fp024.lv02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.lv02.Exam72411Tests.Solution.combinations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 메뉴 리뉴얼
 *   https://school.programmers.co.kr/learn/courses/30/lessons/72411
 */
@Slf4j
class Exam72411Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 72411;

  /*
     🤔 ===== 문제 풀이 전략 ===== 🧠
     1. 일단 조합(combination)에 대해 알고 있어야하고,
        조합 함수를 구현할 수 있어야하는게 중요함. 😂
     2. 그 이후부터는 코스별 카운팅 맵을 잘 구성하면
        문제는 풀 수 있긴 했다. 😅
  */

  // ✨ ========== Target ========== ✨
  static class Solution {

    static List<String> combinations(String menuString, int r) {
      List<String> resultList = new ArrayList<>();
      calcCombinations(0, menuString.toCharArray(), "", resultList, r);
      return resultList;
    }

    // 재귀 실행 영역
    static void calcCombinations(
        int idx, char[] order, String result, List<String> resultList, int r) {
      if (result.length() == r) {
        resultList.add(result);
        return;
      }

      for (int i = idx; i < order.length; i++) {
        calcCombinations(i + 1, order, result + order[i], resultList, r);
      }
    }

    public String[] solution(String[] orders, int[] course) {
      // 코스 단위별(course[]) 가장 많이 주문된 코스단위 조합, 단 2개 이상 주문이 되야함.
      List<String> answer = new ArrayList<>();

      for (int courseQty : course) {
        // <코스 단위, 카운트> 맵
        Map<String, Integer> countUnitCountMap = new HashMap<>();
        for (String orderSet : orders) {
          String sortedOrderSet =
              Arrays.stream(orderSet.split("")).sorted().collect(Collectors.joining(""));
          List<String> combiList = combinations(sortedOrderSet, courseQty);
          // 코스 단위 카운트 맵을 생성
          for (String s : combiList) {
            countUnitCountMap.put(s, countUnitCountMap.getOrDefault(s, 0) + 1);
          }
        }

        int max = 0;
        for (String s : countUnitCountMap.keySet()) {
          // 코스 단위별 가장 많이 나타난 카운트:
          //   예) 코스단위2|3|4 에서 가장 많이 나온 코스단위 카운트
          if (countUnitCountMap.get(s) > max) {
            max = countUnitCountMap.get(s);
          }
        }

        for (String s : countUnitCountMap.keySet()) {
          if (countUnitCountMap.get(s) == max //
              // 코스 단위 별 가장 많이 나온 코스단위를 답안에 추가하는데,
              // 2개 이상일 때만 답안에 추가
              && countUnitCountMap.get(s) >= 2) {
            answer.add(s);
          }
        }
      }

      return answer.stream().sorted().toArray(String[]::new);
    }
  }

  // ✅ ========== Test ========== ✅
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv02.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv02.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String[] orders, int[] course, String[] expect) {
    assertThat(new Solution().solution(orders, course)).isEqualTo(expect);
  }

  @Test
  void testCombinations() {
    String x = "ABC";
    assertThat(combinations(x, 2)).isEqualTo(List.of("AB", "AC", "BC"));
    assertThat(combinations(x, 3)).isEqualTo(List.of("ABC"));
    assertThat(combinations(x, 4)).isEqualTo(List.of());
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  // 조합에 대해 잘 몰라서, 지금도 조합 함수를 정확히 구현을 할 수 있을지? 모르겠지만... 😂
  // 뭔가 힌트들을 봐도 어려웠다.
  // 최근 풀은 문제 중에 최고로 어려웠음 😅
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
