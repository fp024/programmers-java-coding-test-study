package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 문자열 나누기
 *   https://school.programmers.co.kr/learn/courses/30/lessons/140108
 */
@Slf4j
class Exam140108Tests {
  static class Solution {

    /**
     * @param s 문자열 s
     * @return
     */
    public int solution(String s) {

      String partString = s;
      List<String> parts = new ArrayList<>();

      while (!partString.isEmpty()) {
        String part = getPart(partString);

        if (part.isEmpty()) {
          break;
        } else {
          parts.add(part);
          partString = partString.replaceFirst(part, "");
        }
      }

      System.out.println(parts);
      return parts.size();
    }

    private String getPart(String s) {

      final var firstChar = s.charAt(0);
      int sameCount = 1; // 첫글자는 이미 한번 일치로 간주
      int diffCount = 0;
      int splitIndex = 0;

      for (int i = 1; i < s.length(); i++) {

        if (firstChar == s.charAt(i)) {
          sameCount++;
        } else {
          diffCount++;
        }

        if (sameCount <= diffCount) {
          splitIndex = i;
          break;
        }
      }

      return s.substring(0, splitIndex + 1);
    }

    @ParameterizedTest
    @MethodSource({
      "org.fp024.lv01.Exam140108TestData#defaultDataProvider", //
      // "org.fp024.lv01.Exam140108TestData#extraDataProvider"
    })
    void testSolution(String s, int expect) {
      assertThat(new Solution().solution(s)).isEqualTo(expect);
    }

    // cspell:enable
    //
    // === 문제 읽고 첫 느낌 ===
    //   이것도 어렵다...😂😂😂
    //   일단 한 부분을 구하는 메서드를 먼저 작성해두니, 결국은 기본 테스트 케이스로는 통과긴한데..
    //   4,7,35,36,37,41 테스트 케이스가 실패한다.
    //
    // === 다른 사람 풀이 확인 이후 의견 ===
    //   ...
    //
  }
}
