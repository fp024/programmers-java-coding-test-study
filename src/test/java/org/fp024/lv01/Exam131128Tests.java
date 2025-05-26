package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 숫자 짝꿍
 *   https://school.programmers.co.kr/learn/courses/30/lessons/131128
 */
@Slf4j
class Exam131128Tests {
  // ========== Target ==========
  static class Solution {
    public String solution(String x, String y) {
      String answer;

      List<String> descX =
          Arrays.stream(x.split(""))
              .sorted((a, b) -> Integer.compare(Integer.parseInt(b), Integer.parseInt((a))))
              .collect(Collectors.toList());

      List<String> descY =
          Arrays.stream(y.split(""))
              .sorted((a, b) -> Integer.compare(Integer.parseInt(b), Integer.parseInt((a))))
              .collect(Collectors.toList());

      final List<String> longer;
      final List<String> shorter;

      if (descX.size() > descY.size()) {
        longer = descX;
        shorter = descY;
      } else {
        longer = descY;
        shorter = descX;
      }

      StringBuilder result = new StringBuilder();

      for (String s : shorter) {
        int foundIndex = longer.indexOf(s);
        if (foundIndex != -1) {
          longer.set(foundIndex, "");
          result.append(s);
        }
      }

      if (result.length()
          == 0) { // 💡Java 15부터는 StringBuilder에 isEmpty()가 있는데, 프로그래머스의 JDK는 Java 14라서 length로
        // 검사해야한다.
        answer = "-1";
      } else {
        answer = Integer.valueOf(result.toString()).toString();
      }

      return answer;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam131128TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam131128TestData#extraDataProvider"
  })
  void testSolution(String x, String y, String expect) {
    assertThat(new Solution().solution(x, y)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   그렇게 어려운 문제는 아닐 것 같았는데, 좀 시간이 걸렸다.
  //   `.collect(Collectors.toList());`는 변경 가능 리스트
  //   `toList();`는 불변 리스트를 반환의 차이가 있었구나...
  //    불변으로 하면 remove 및 내용 변경(set)도 안된다.
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
