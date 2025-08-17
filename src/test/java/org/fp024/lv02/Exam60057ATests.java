package org.fp024.lv02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.lv02.Exam60057Tests.Solution.zip;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 문자열 압축 - Claude 4 코드리뷰 제안 코드
 *   https://school.programmers.co.kr/learn/courses/30/lessons/60057
 */
@Slf4j
class Exam60057ATests {
  /** 문제 번호 */
  private static final int EXAM_NO = 60057;

  /*
     🤖 ===== 문제 풀이 전략 ===== 🤖
       후처리를 줄이고 메인 루프에서 처리하는 방식으로 개선
       더 직관적인 구조로 변경
  */

  // ✨ ========== Target ========== ✨
  static class Solution {

    /**
     * 문자열 압축
     *
     * @param s 압축할 문자열
     * @param unitLength 압축 단위
     * @return 압축된 문자열
     */
    static String zip(String s, int unitLength) {
      if (unitLength > s.length()) {
        return s;
      }

      StringBuilder result = new StringBuilder();

      for (int i = 0; i < s.length(); i += unitLength) {
        String current = s.substring(i, Math.min(i + unitLength, s.length()));

        // 현재 단위가 완전하지 않으면(마지막 부분) 그대로 추가하고 종료
        if (current.length() != unitLength) {
          result.append(current);
          break;
        }

        int count = 1;

        // 다음 패턴들과 비교하여 연속된 개수 세기
        while (i + count * unitLength + unitLength <= s.length()) {
          String next = s.substring(i + count * unitLength, i + (count + 1) * unitLength);
          if (current.equals(next)) {
            count++;
          } else {
            break;
          }
        }

        // 결과에 추가
        if (count > 1) {
          result.append(count);
        }
        result.append(current);

        // 처리한 만큼 건너뛰기 (for문의 i += unitLength와 합쳐짐)
        i += (count - 1) * unitLength;
      }

      return result.toString();
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
  // ...
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
