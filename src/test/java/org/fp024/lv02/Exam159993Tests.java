package org.fp024.lv02;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 미로 탈출
 *   https://school.programmers.co.kr/learn/courses/30/lessons/159993
 */
@Slf4j
class Exam159993Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 159993;

  // ========== Target ==========
  static class Solution {

    /** 미로 요소 */
    enum MazeElement {
      S("시작"),
      E("종료"),
      X("벽"),
      L("레버");
      private final String description;

      MazeElement(final String description) {
        this.description = description;
      }

      public String description() {
        return this.description;
      }

      public char code() {
        return name().charAt(0);
      }
    }

    /** 좌표 타입 */
    static class Point {
      public int x;
      public int y;

      /**
       * 해당 좌표(x, y)로 이동이 가능한지?
       *
       * @param maps 미로
       * @return 이동가능하면 true, 아니면 false
       */
      public boolean isMoveable(String[] maps) {
        int rows = maps.length;
        int cols = maps[0].length();
        return (this.y >= 0 && this.y < rows) //
            && (this.x >= 0 && this.x < cols) //
            && maps[this.y].charAt(this.x) != MazeElement.X.code();
      }

      public Point(int x, int y) {
        this.x = x;
        this.y = y;
      }

      @Override
      public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
      }

      @Override
      public int hashCode() {
        return Objects.hash(x, y);
      }
    }

    /** 큐 원소 타입 */
    static class QueueItem {
      /** 현재 좌표 */
      public Point point;

      /** 레버가 당겨진 상태인지 여부 (false=미당김, true=당김) */
      public boolean onLever;

      /** 시작점으로부터 이동 시간(이동 횟수) */
      public int time;

      public QueueItem(Point point, boolean onLever, int time) {
        this.point = point;
        this.onLever = onLever;
        this.time = time;
      }
    }

    /** 방문 상태 */
    static class VisitStatus {
      /** 레버 off일 때 방문 여부 */
      public boolean offLever;

      /** 레버 on 일 때 방문 여부 */
      public boolean onLever;

      public VisitStatus(boolean offLever, boolean onLever) {
        this.offLever = offLever;
        this.onLever = onLever;
      }

      /** 레버 On/Off 상황에 따라 방문 여부 업데이트 */
      public void update(boolean onLever) {
        if (onLever) {
          this.onLever = true;
        } else {
          this.offLever = true;
        }
      }

      /** 레버 On/Off 여부에 따른 방문 상태 얻기 */
      public boolean get(boolean onLever) {
        return onLever ? this.onLever : this.offLever;
      }
    }

    /**
     * 큐에 새로 이동할 정보 추가
     *
     * @param point 새로 이동할 좌표
     * @param onLever 레버 상태 (false=미당김, true=당김)
     * @param time 현재까지의 이동 시간(이동 횟수). 새 칸은 time + 1로 큐에 들어간다.
     * @param visited 방문 여부
     * @param queue 이동 정보 기록 큐
     */
    void appendToQueue(
        Point point, //
        boolean onLever,
        int time,
        List<List<VisitStatus>> visited,
        Queue<QueueItem> queue) {

      if (!visited.get(point.y).get(point.x).get(onLever)) {
        visited.get(point.y).get(point.x).update(onLever);
        queue.add(new QueueItem(point, onLever, time + 1));
      }
    }

    public int solution(String[] maps) {
      int rows = maps.length;
      int cols = maps[0].length();

      List<List<VisitStatus>> visited = new ArrayList<>(rows);
      for (var i = 0; i < rows; i++) {
        visited.add(new ArrayList<>());
        for (var j = 0; j < cols; j++) {
          visited.get(i).add(new VisitStatus(false, false));
        }
      }

      // 위, 아래, 왼쪽, 오른쪽
      int[] dy = {-1, 1, 0, 0};
      int[] dx = {0, 0, -1, 1};

      Queue<QueueItem> queue = new ArrayDeque<>();
      Point endPoint = new Point(-1, -1);

      for (var i = 0; i < rows; i++) {
        for (var j = 0; j < cols; j++) {
          if (maps[i].charAt(j) == MazeElement.S.code()) {
            queue.add(new QueueItem(new Point(j, i), false, 0));
            visited.get(i).get(j).update(false);
          }
          if (maps[i].charAt(j) == MazeElement.E.code()) {
            endPoint.y = i;
            endPoint.x = j;
          }
        }
      }

      while (!queue.isEmpty()) {
        QueueItem queueItem = queue.poll();
        Point currentPoint = queueItem.point;

        // 도착점에 도달하면 결과 반환
        if (currentPoint.equals(endPoint) && queueItem.onLever) {
          return queueItem.time;
        }

        // 4 방향 이동
        for (var i = 0; i < 4; i++) {
          Point nextPoint = new Point(currentPoint.x + dx[i], currentPoint.y + dy[i]);

          if (!nextPoint.isMoveable(maps)) {
            continue;
          }

          if (maps[nextPoint.y].charAt(nextPoint.x) == MazeElement.L.code()) {
            // 여기서 부터 onLever가 true(당겨짐)이 설정된 후에는 다음 부터는 계속 당겨진 상태가 유지됨.
            appendToQueue(nextPoint, true, queueItem.time, visited, queue);
          } else {
            //  다음 이동 지점이 레버가 아닌 경우 (onLever의 상태는 그대로 유지)
            appendToQueue(nextPoint, queueItem.onLever, queueItem.time, visited, queue);
          }
        }
      }

      return -1;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv02.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv02.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String[] maps, int expect) {
    assertThat(new Solution().solution(maps)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   여전히 처음부터 풀기가 힘들것 같긴하다. 😂
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
