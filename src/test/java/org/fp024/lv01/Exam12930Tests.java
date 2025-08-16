package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 택배 상자 꺼내기
 *   https://school.programmers.co.kr/learn/courses/30/lessons/12930
 */
@Slf4j
class Exam12930Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 12930;

  // ========== Target ==========
  static class Solution {
    public String solution(String s) {

      StringBuilder sb = new StringBuilder(s);

      for (int i = 0, wordStartIndex = 0; i < s.length(); i++) {
        if (sb.charAt(i) == ' ') {
          // 💡공백을 찾을 때마다 공백의 다음을 단어 시작 인덱스로 간주
          wordStartIndex = i + 1;
          continue;
        }

        if ((i - wordStartIndex) % 2 == 0) {
          sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
        } else {
          sb.setCharAt(i, Character.toLowerCase(sb.charAt(i)));
        }
      }

      return sb.toString();
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String s, String expect) {
    assertThat(new Solution().solution(s)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   약간 함정이 있는 문제인데,
  //   원본 문자에 공백이 여러개 있을때,
  //   변환 결과에도 그 공백 크기가 똑같이 유지되야한다.
  //
  // ts로도 예전에 풀긴 풀었음. 😊
  // https://github.com/fp024/programmers-js-coding-test-study/blob/master/src/ts/lv_1/exam029-12930.ts
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
