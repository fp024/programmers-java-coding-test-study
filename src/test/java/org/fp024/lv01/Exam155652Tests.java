package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 둘만의 암호
 *   https://school.programmers.co.kr/learn/courses/30/lessons/155652
 */
@Slf4j
class Exam155652Tests {
  /** 문제 번호 */
  private static final String EXAM_NO = "155652";

  // ========== Target ==========
  static class Solution {

    /**
     * @param s 문자열 s
     * @param skip
     * @param index
     * @return
     */
    public String solution(String s, String skip, int index) {
      StringBuilder answer = new StringBuilder();

      final String skippedAlphabet = "abcdefghijklmnopqrstuvwxyz".replaceAll("[" + skip + "]", "");

      for (int i = 0; i < s.length(); i++) {
        int c = s.charAt(i);

        int newIndex;
        if ((skippedAlphabet.indexOf(c) + index) >= skippedAlphabet.length()) {
          newIndex = (skippedAlphabet.indexOf(c) + index) % skippedAlphabet.length();
        } else {
          newIndex = skippedAlphabet.indexOf(c) + index;
        }

        answer.append(skippedAlphabet.charAt(newIndex));
      }

      return answer.toString();
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String s, String skip, int index, String expect) {
    assertThat(new Solution().solution(s, skip, index)).isEqualTo(expect);
  }
  //
  // === 문제 읽고 첫 느낌 ===
  //   일단...
  //    1. skip 문자를 정리한 알파벳 문자열을 만듬.
  //    2. 1.에서 만든 문자열을 기준 문자열로 참고해서 새로운 인덱스를 구한다.
  //       오버플로우를 고려해서 나머지 연산 처리를 한다.
  //       그런데.. 무조건 나머지 연산을 하면 성능을 조금이라도 더 잡아먹을 수 있으니, if 검사후에 조건이 맞으면 나머지연산을 함.
  //    3. 새로운 인덱스 값을 1에서 만든 문자열 기준에서 찾아서 응답 문자열에 누적
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //    다들 잘 푸신 것 같다, 이번에는 +2점 받음.
  //
  //
}
