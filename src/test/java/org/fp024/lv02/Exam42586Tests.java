package org.fp024.lv02;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 기능 개발
 *   https://school.programmers.co.kr/learn/courses/30/lessons/42586
 */
@Slf4j
class Exam42586Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 42586;

  /*
     🤔 ===== 문제 풀이 전략 ===== 🧠
     이미 Python, JS로 이미 풀어서인지...할말이 없음 😅
  */

  // ✨ ========== Target ========== ✨
  static class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
      final int N = progresses.length;
      int[] releaseLeftDays = new int[N];

      for (int i = 0; i < N; i++) {
        releaseLeftDays[i] = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
      }

      log.info("배포 예상 남은 일 수: {}", Arrays.toString(releaseLeftDays));

      List<Integer> answer = new ArrayList<>();

      int maxDaysInGroup = releaseLeftDays[0];
      int count = 0;

      for (int i = 0; i < N; i++) {
        if (maxDaysInGroup >= releaseLeftDays[i]) {
          count++;
        } else {
          maxDaysInGroup = releaseLeftDays[i];
          answer.add(count);
          // 💡 다음 배포 전환시 count는 1로 초기화
          count = 1;
        }
      }
      answer.add(count);

      return answer.stream().mapToInt(Integer::intValue).toArray();
    }
  }

  // ✅ ========== Test ========== ✅
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv02.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv02.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(int[] progresses, int[] speeds, int[] expect) {
    assertThat(new Solution().solution(progresses, speeds)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  // * TS
  // https://github.com/fp024/programmers-js-coding-test-study/blob/master/src/js/lv_2/exam001-42586.js
  // * Python
  // https://github.com/fp024/programmers-python-coding-test-study/blob/master/src/lv02/exam001_42586.py
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
