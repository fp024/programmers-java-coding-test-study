package org.fp024.lv02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.lv02.Exam60057Tests.Solution.zip;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 문자열 압축
 *   https://school.programmers.co.kr/learn/courses/30/lessons/60057
 */
@Slf4j
class Exam60057Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 60057;

  /*
     🤔 ===== 문제 풀이 전략 ===== 🧠
       뭔가 어거지로 풀은 느낌이 강한데 😅
       일찌감치 zip()에 대한 테스트 코드를 먼저 작성해보면서,
       진행을 해서 그런 것 같기도 하고...😅
  */

  // ✨ ========== Target ========== ✨
  static class Solution {

    /**
     * 문자열 압축
     *
     * @param s 압축할 문자열
     * @param unitLength 압축 단위
     * @return 압축이 되지않을 경우 원본 문자열 반환
     */
    static String zip(String s, int unitLength) {
      StringBuilder zipBuilder = new StringBuilder();

      int start = 0;

      int count = 1;

      String prevUnit = "";
      while (start + unitLength <= s.length()) {
        String currentUnit = s.substring(start, start + unitLength);

        if (prevUnit.equals(currentUnit)) {
          count++;
        } else {
          if (count > 1) {
            zipBuilder.append(count);
          }
          zipBuilder.append(prevUnit);
          count = 1;
        }

        prevUnit = currentUnit;
        start = start + unitLength;
      }
      // 뒷 부분 후처리: 이걸 반복에 넣기가 힘들다. 😅
      if (count > 1) {
        zipBuilder.append(count);
      }

      zipBuilder.append(prevUnit);
      // unit 단위에 포함되지 않은 문자열 추가
      zipBuilder.append(s.substring(start));

      return zipBuilder.toString();
    }

    public int solution(String s) {
      // 초기값은 압축 불가능한 상태
      int answer = s.length();

      for (int unit = 1; unit <= s.length() / 2; unit++) {
        var zipString = zip(s, unit);
        log.info("zipString={}, unit={}", zipString, unit);
        answer = Math.min(answer, zipString.length());
      }

      return answer;
    }
  }

  // ✅ ========== Test ========== ✅
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv02.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv02.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String s, int expect) {
    assertThat(new Solution().solution(s)).isEqualTo(expect);
  }

  @Test
  void testZip01() {
    var zipped = zip("aaabbbccc", 1);
    assertThat(zipped).isEqualTo("3a3b3c");
  }

  @Test
  void testZip02() {
    var zipped = zip("abc", 1);
    assertThat(zipped).isEqualTo("abc");
  }

  @Test
  void testZip03() {
    var zipped = zip("abc", 3);
    assertThat(zipped).isEqualTo("abc");
  }

  @Test
  void testZip04() {
    var zipped = zip("aabbaccc", 3);
    assertThat(zipped).isEqualTo("aabbaccc");
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   뭔가 어려웠는데... 일단 +2점으로 통과는 했다. 😅
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
