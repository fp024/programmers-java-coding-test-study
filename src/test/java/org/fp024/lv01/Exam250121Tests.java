package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * [PCCE 기출문제] 10번 / 데이터 분석
 *   https://school.programmers.co.kr/learn/courses/30/lessons/250121
 */
@Slf4j
class Exam250121Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 250121;

  // ========== Target ==========
  static class Solution {

    /** 컬럼 정의 */
    enum Column {
      CODE(0),
      DATE(1),
      MAXIMUM(2),
      REMAIN(3);

      private final int index;

      Column(int index) {
        this.index = index;
      }

      public static int getIndex(String columnName) {
        return valueOf(columnName.toUpperCase()).index;
      }
    }

    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {

      return Arrays.stream(data) //
          .filter(row -> row[Column.getIndex(ext)] < val_ext) //
          .sorted(Comparator.comparingInt(o -> o[Column.getIndex(sort_by)]))
          .toArray(int[][]::new);
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(int[][] data, String ext, int val_ext, String sort_by, int[][] expect) {
    assertThat(new Solution().solution(data, ext, val_ext, sort_by)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   이건 왠지 JS/TS 환경 에서 고차함수 사용하면 아주 편했을 것 같은데... 😅
  //   그래도 한번에 잘 풀렸다.
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   다른 사람들도 스트림 사용해서 비슷 비슷하게 풀은 것 같다. 😊
  //
}
