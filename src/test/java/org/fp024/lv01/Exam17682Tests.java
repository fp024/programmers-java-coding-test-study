package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * [1차] 다트 게임
 *   https://school.programmers.co.kr/learn/courses/30/lessons/17682
 */
@Slf4j
class Exam17682Tests {
  static class Solution {
    /**
     * @param dartResult "점수|보너스|[옵션]" 으로 구성된 문자열
     * @return 총점
     */
    public int solution(String dartResult) {
      int answer = 0;
      // 라운드별 입력 로그
      String[] roundLog = new String[3];
      // 라운드별 스코어
      int[] scores = new int[3];
      // star 곱 배열
      int[] mulStar = {1, 1, 1};

      Pattern pattern = Pattern.compile("(\\d+)([SDT])([*#]?)");
      Matcher matcher = pattern.matcher(dartResult);

      int minusShapPosition = -1;

      // 라운드 분리
      for (int i = 0; i < roundLog.length && matcher.find(); i++) {
        roundLog[i] = matcher.group();
        if (roundLog[i].contains("*")) {
          for (int j = Math.max(i - 1, 0); j <= i; j++) {
            mulStar[j] = mulStar[j] * 2;
          }
        }
        if (roundLog[i].contains("#")) {
          minusShapPosition = i;
        }
      }

      System.out.println(Arrays.toString(roundLog));
      System.out.printf("%d, %s%n", minusShapPosition, Arrays.toString(mulStar));

      // 영역 계산
      for (int i = roundLog.length - 1; i >= 0; i--) {
        Matcher m = pattern.matcher(roundLog[i]);
        if (m.matches()) {
          int score = Integer.parseInt(m.group(1));

          String bonus = m.group(2);

          if (bonus.equals("D")) {
            score = score * score;
          } else if (bonus.equals("T")) {
            score = score * score * score;
          }

          score = score * mulStar[i];

          if (minusShapPosition == i) {
            score = score * -1;
          }
          scores[i] = score;
        }
      }

      System.out.println(Arrays.toString(scores));
      // 옵션 계산

      for (int score : scores) {
        answer += score;
      }

      return answer;
    }
  }

  @ParameterizedTest
  @MethodSource("defaultDataProvider")
  void testSolution(String dartResult, int expect) {
    assertThat(new Solution().solution(dartResult)).isEqualTo(expect);
  }

  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            "1S2D*3T", //
            37
            //
            ),
        Arguments.of(
            "1D2S#10S", //
            9
            //
            ),
        Arguments.of(
            "1D2S0T", //
            3
            //
            ),
        Arguments.of(
            "1S*2T*3S", //
            23
            //
            ),
        Arguments.of(
            "1D#2S*3S", //
            5
            //
            ),
        Arguments.of(
            "1T2D3D#", //
            -4
            //
            ),
        Arguments.of(
            "1D2S3T*", //
            59
            //
            )
        //
        );
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   진짜 어렵다.. 정규식을 써도... 옵션에서 * 적용하는게 좀 어려운 것 같음.
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
