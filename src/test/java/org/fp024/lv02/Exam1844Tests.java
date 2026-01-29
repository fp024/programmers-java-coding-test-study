package org.fp024.lv02;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 게임 맵 최단 거리
 *   https://school.programmers.co.kr/learn/courses/30/lessons/1844
 */
@Slf4j
class Exam1844Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 1844;

  // ========== Target ==========
  static class Solution {

    /** 이동한 위치 상태 */
    private static class PointState {
      int x;
      int y;
      int times;

      PointState(int x, int y, int times) {
        this.x = x;
        this.y = y;
        this.times = times;
      }
    }

    /** 지점 이동 가능 여부 */
    boolean isValidMove(int ny, int nx, int row, int col, int[][] maps) {
      return 0 <= ny //
          && ny < row
          && 0 <= nx
          && nx < col
          && maps[ny][nx] != 0;
    }

    public int solution(int[][] maps) {
      int row = maps.length;
      int col = maps[0].length;

      // 방문 여부
      boolean[][] visited = new boolean[row][col];

      Queue<PointState> queue = new ArrayDeque<>();

      // 위, 아래, 왼쪽, 오른쪽 이동 방향
      int[] dy = {-1, 1, 0, 0};
      int[] dx = {0, 0, -1, 1};

      // 시작 좌표
      int startY = 0;
      int startX = 0;

      // 도착점 좌표
      int endY = row - 1;
      int endX = col - 1;

      // 시작 좌표 큐에 넣음
      queue.add(new PointState(startX, startY, 0));

      while (!queue.isEmpty()) {
        PointState state = queue.poll();

        // 도착점에 도달하면 결과 반환
        if (state.y == endY && state.x == endX) {
          return state.times + 1; // 💡 출발지를 포함해야해서 1을 더해줘야했다.
        }

        // 4방향 이동
        for (int k = 0; k < 4; k++) {
          int ny = state.y + dy[k];
          int nx = state.x + dx[k];

          // 이동 가능한 좌표만 큐에 넣음
          if (!isValidMove(ny, nx, row, col, maps)) {
            continue;
          }

          // 방문 했던 지점은 넘어감
          if (visited[ny][nx]) {
            continue;
          }

          queue.add(new PointState(nx, ny, state.times + 1));
          visited[ny][nx] = true;
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
  void testSolution(int[][] maps, int expect) {
    assertThat(new Solution().solution(maps)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  // 예전에 더 어려운 문제... 래버 개념까지 있었던... 아래 문제의...
  // https://github.com/fp024/programmers-js-coding-test-study/blob/master/src/ts/lv_2/exam015-159993.ts
  // 레버개념만 제외해서 적용해봤다.. 😅😅
  // 지금 문제가 위의 링크의 문제보다 고려할 것이 적은 쉬운문제다. 😊
  //
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
