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
  /** 문제 번호 */
  private static final int EXAM_NO = 140108;

  // ========== Target ==========
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
      int firstCharCount = 1; // 첫글자는 이미 한번 나온 것으로 간주
      int diffCharCount = 0;
      int splitIndex = 0;

      for (int i = 1; i < s.length(); i++) {

        if (firstChar == s.charAt(i)) {
          firstCharCount++;
        } else {
          diffCharCount++;
        }

        if (firstCharCount <= diffCharCount) {
          splitIndex = i;
          break;
        }
      }

      // 잘라내야할 인덱스를 한번도 못찾았을 때는, 문자열그대로 반환한다.
      return s.substring(0, splitIndex == 0 ? s.length() : splitIndex + 1);
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String s, int expect) {
    assertThat(new Solution().solution(s)).isEqualTo(expect);
  }
  // cspell:disable
  //
  // === 문제 읽고 첫 느낌 ===
  //   이것도 어렵다...😂😂😂
  //   일단 한 부분을 구하는 메서드를 먼저 작성해두니, 결국은 기본 테스트 케이스로는 통과긴한데..
  //   4,7,35,36,37,39,41 테스트 케이스가 실패한다.
  //
  //   "aaba"와 같은 문자열이면 잘래낼 경우가 없어서 들어온 그대로 문자열을 반환할 필요가 있었는데,
  //   그 처리가 없어서 문제가 있었다.
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   반복 한번으로도 잘들 끝내는 것 같은데... 바로 이해가 안된다. 😂
  //
}
