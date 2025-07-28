package org.fp024.lv02;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.util.StopWatch;

/*
 * 주식가격
 *   https://school.programmers.co.kr/learn/courses/30/lessons/42584
 */
@Slf4j
class Exam42584Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 42584;

  // ========== Target ==========
  static class Solution {
    public int[] solution(int[] prices) {
      final int N = prices.length;
      int[] answer = new int[N];

      for (int i = 0; i < N; i++) {
        for (int j = i + 1; j < N; j++) {
          answer[i]++;
          if (prices[i] > prices[j]) {
            break;
          }
        }
      }

      return answer;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({"org.fp024.lv02.Exam" + EXAM_NO + "TestData#defaultDataProvider"})
  void testSolution(int[] prices, int[] expect) {
    assertThat(new Solution().solution(prices)).isEqualTo(expect);
  }

  @ParameterizedTest
  @MethodSource({"org.fp024.lv02.Exam" + EXAM_NO + "TestData#extraDataProvider"})
  void testSolution_ExtraData(int[] prices, int[] expect) {
    var exam = new Solution();
    var stopWatch = new StopWatch();

    stopWatch.start();
    var result = exam.solution(prices);
    stopWatch.stop();

    log.info("입력 데이터 수: {}", prices.length);
    log.info("수행시간(ns): {}", stopWatch.getTotalTimeNanos());
    log.info("수행시간(sec): {}", stopWatch.getTotalTimeSeconds());

    assertThat(result).isEqualTo(expect);
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
