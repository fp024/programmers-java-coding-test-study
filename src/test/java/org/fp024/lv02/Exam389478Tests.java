package org.fp024.lv02;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 카펫 ★★
 *   https://school.programmers.co.kr/learn/courses/30/lessons/42842
 */
@Slf4j
class Exam389478Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 389478;

  // ========== Target ==========
  static class Solution {
    public int[] solution(int brown, int yellow) {
      int[] answer = new int[2];

      // 노란색 영역의 넓이 목록
      List<Dimension> yellowDimensionList = getDivisorPairs(yellow);

      for (Dimension dimension : yellowDimensionList) {
        var brownWidth = dimension.width + 2; // brown의 가로 길이
        var brownHeight = dimension.height + 2; // brown의 세로 길이

        // 가로 * 세로가 = brown과 yello의 합과 같은 경우가 구하려는 카펫의 크기이다.
        if (brownWidth * brownHeight == brown + yellow) {
          answer[0] = brownWidth;
          answer[1] = brownHeight;
        }
      }

      return answer;
    }

    List<Dimension> getDivisorPairs(int n) {
      List<Dimension> dimensionList = new ArrayList<>();
      for (int i = 1; i * i <= n; i++) {
        if (n % i == 0) {
          dimensionList.add(new Dimension(n / i, i));
        }
      }
      return dimensionList;
    }

    record Dimension(int width, int height) {}
    // 프로그래머스에 제출할 때는 record 대신 class로 제출 해야한다. (JDK 16부터 record 지원)
    /*
    static class Dimension {
      final int width;
      final int height;

      Dimension(int width, int height) {
        this.width = width;
        this.height = height;
      }
    }
    */
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv02.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv02.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(int brown, int yellow, int[] expect) {
    assertThat(new Solution().solution(brown, yellow)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  // C++로 먼저 풀은 내용을 Java로 옮겼다. 주요 로직은 아래와 동일하다.
  // https://github.com/fp024/programmers-c-coding-test-study/blob/master/lv02/Exam001_42842.cpp
  // https://github.com/fp024/programmers-c-coding-test-study/blob/master/lv02/Exam001_42842_test.cpp
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
