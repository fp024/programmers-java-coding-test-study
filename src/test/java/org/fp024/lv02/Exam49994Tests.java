package org.fp024.lv02;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 방문길이 ★★
 *   https://school.programmers.co.kr/learn/courses/30/lessons/49994
 */
@Slf4j
class Exam49994Tests {
  /** 프로그래머스 문제 번호 */
  private static final String EXAM_NO = "49994";

  // ✨ ========== Target ========== ✨
  static class Solution {

    static class Point {
      int x;
      int y;

      Point(int x, int y) {
        this.x = x;
        this.y = y;
      }

      @Override
      public String toString() {
        return "(" + x + "," + y + ")";
      }
    }

    enum MoveCommand {
      U(0, 1),
      D(0, -1),
      R(1, 0),
      L(-1, 0);

      private final int x;
      private final int y;

      MoveCommand(int x, int y) {
        this.x = x;
        this.y = y;
      }
    }

    /** 이동할 새로운 좌표 얻기 */
    Point createNewPoint(Point currentPoint, MoveCommand command) {
      return new Point(currentPoint.x + command.x, currentPoint.y + command.y);
    }

    /** 사용 가능한 좌표인지? */
    boolean isAvailablePoint(Point point) {
      return Math.abs(point.x) <= 5 && Math.abs(point.y) <= 5;
    }

    public int solution(String dirs) {
      // 처음 좌표 위치
      Point point = new Point(0, 0);

      Set<String> moveSet = new HashSet<>();

      for (String dir : dirs.split("")) {
        Point newPoint = createNewPoint(point, MoveCommand.valueOf(dir));

        if (!isAvailablePoint(newPoint)) {
          continue;
        }

        moveSet.add(point.toString() + newPoint);
        // 역 방향 이동 흐름도 같이 저장
        moveSet.add(newPoint.toString() + point);

        point = newPoint;
      }

      // 역방향 내용을 항상 같이 저장했으므로 반으로 나눈다.
      return moveSet.size() / 2;
    }
  }

  // ✅ ========== Test ========== ✅
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv02.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    "org.fp024.lv02.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String dirs, int expect) {
    assertThat(new Solution().solution(dirs)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  // 💡 TS 풀이
  // https://github.com/fp024/programmers-js-coding-test-study/blob/master/src/ts/lv_2/exam011-49994.ts
  // TS로 먼저 풀고 하다보니 좀 쉽게 진행한 것 같다. 👍
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
