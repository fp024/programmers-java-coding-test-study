package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * [1차] 다트 게임 - AI 코드 정리
 *   https://school.programmers.co.kr/learn/courses/30/lessons/17682
 */
@Slf4j
class Exam17682ATests {
  /** 문제 번호 */
  private static final String EXAM_NO = "17682";

  // ========== Target ==========
  static class Solution {
    /**
     * @param dartResult "점수|보너스|[옵션]" 으로 구성된 문자열
     * @return 총점
     */
    public int solution(String dartResult) {
      Pattern pattern = Pattern.compile("(\\d+)([SDT])([*#]?)");
      Matcher matcher = pattern.matcher(dartResult);

      int[] scores = new int[3];
      int i = 0;

      // 점수 계산
      while (matcher.find() && i < 3) {
        int score = Integer.parseInt(matcher.group(1));
        String bonus = matcher.group(2);
        String option = matcher.group(3);

        // 보너스 적용
        if (bonus.equals("D")) {
          score = (int) Math.pow(score, 2);
        } else if (bonus.equals("T")) {
          score = (int) Math.pow(score, 3);
        }

        // 옵션 적용
        if (option.equals("*")) {
          score *= 2;
          if (i > 0) scores[i - 1] *= 2; // 이전 점수도 2배
        } else if (option.equals("#")) {
          score *= -1;
        }

        scores[i++] = score;
      }

      // 총점 계산
      return Arrays.stream(scores).sum();
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String dartResult, int expect) {
    assertThat(new Solution().solution(dartResult)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  // ...
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   내가 이전에 작성한 코드가 거지같은데.. 한번 볼래 했더니 위처럼 정리해줬다.
  //   훨씬 낫네 👍
  //   1. 주요 반복이 1회라서 보기가 좋다.
  //   2. star의 이전 점수 처리도 좋다. 난 곱하는 수를 배열로 미리 만들어놨었었음 😅
  //
}
