package org.fp024.lv00;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 코딩 테스트 입문 03
 * https://school.programmers.co.kr/learn/challenges/beginner?order=acceptance_desc
 *
 * 4개 문제 남긴 했는데... 점점 어려워져서 일찍 클래스를 분리했다. 😂
 */
@Slf4j
class BeginnerExam03Tests {

  /*
   * 💡 안전지대
   *     https://school.programmers.co.kr/learn/courses/30/lessons/120866
   *
   * 힌트: https://school.programmers.co.kr/questions/45347
   *
   */
  @ParameterizedTest
  @MethodSource("test_120866_dataProvider")
  void test_120923(int[][] board, int expect) {

    int answer = 0;

    // 가로, 세로가 각각 한칸씩 증가되어있는 보드
    int[][] extendBoard = new int[board.length + 2][board[0].length + 2];

    // 배열 채워넣기
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[0].length; j++) {
        extendBoard[i + 1][j + 1] = board[i][j];
      }
    }

    log.info("===== 확장 배열에 초기 데이터 복사 후 결과 출력 =====");
    // extendBoard 출력
    for (int i = 0; i < extendBoard.length; i++) {
      for (int j = 0; j < extendBoard[i].length; j++) {
        System.out.print(extendBoard[i][j] + " ");
      }
      System.out.println();
    }

    log.info("===== 확장 배열에서 순회하면서 지뢰 마킹 =====");
    final int MINE_POSITION = 1;
    final int RANGE_BLAST = 2;
    final int SAFE_POSITION = 0;
    for (int i = 1; i < extendBoard.length - 1; i++) {
      for (int j = 1; j < extendBoard[i].length - 1; j++) {

        if (extendBoard[i][j] == MINE_POSITION) {
          // 좌상 (↖)
          if (extendBoard[i - 1][j - 1] == SAFE_POSITION) {
            extendBoard[i - 1][j - 1] = RANGE_BLAST;
          }

          // 상 (↑)
          if (extendBoard[i - 1][j] == SAFE_POSITION) {
            extendBoard[i - 1][j] = RANGE_BLAST;
          }

          // 우상 (↗)
          if (extendBoard[i - 1][j + 1] == SAFE_POSITION) {
            extendBoard[i - 1][j + 1] = RANGE_BLAST;
          }

          // 좌 (←)
          if (extendBoard[i][j - 1] == SAFE_POSITION) {
            extendBoard[i][j - 1] = RANGE_BLAST;
          }

          // 우 (→)
          if (extendBoard[i][j + 1] == SAFE_POSITION) {
            extendBoard[i][j + 1] = RANGE_BLAST;
          }

          // 좌하 (↙)
          if (extendBoard[i + 1][j - 1] == SAFE_POSITION) {
            extendBoard[i + 1][j - 1] = RANGE_BLAST;
          }
          // 하 (↓)
          if (extendBoard[i + 1][j] == SAFE_POSITION) {
            extendBoard[i + 1][j] = RANGE_BLAST;
          }
          // 우하 (↘)
          if (extendBoard[i + 1][j + 1] == SAFE_POSITION) {
            extendBoard[i + 1][j + 1] = RANGE_BLAST;
          }
        }
      }
      System.out.println();
    }

    log.info("===== 확장 배열에 영역 마킹 내용 출력 =====");
    // extendBoard 출력
    for (int i = 0; i < extendBoard.length; i++) {
      for (int j = 0; j < extendBoard[i].length; j++) {
        System.out.print(extendBoard[i][j] + " ");
      }
      System.out.println();
    }

    // 안전지역 카운트 세기
    for (int i = 1; i < extendBoard.length - 1; i++) {
      for (int j = 1; j < extendBoard[i].length - 1; j++) {
        if (extendBoard[i][j] == 0) {
          answer++;
        }
      }
    }

    log.info("안전지역 수: {}", answer);

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120866_dataProvider() {
    return Stream.of(
        Arguments.of(
            new int[][] {
              {0, 0, 0}, //
              {0, 0, 0},
              {0, 0, 1},
            }, //
            5
            //
            ),
        Arguments.of(
            new int[][] {
              {0, 0, 0, 0, 0}, //
              {0, 0, 0, 0, 0},
              {0, 0, 0, 0, 0},
              {0, 0, 1, 0, 0},
              {0, 0, 0, 0, 0}
            }, //
            16
            //
            ),
        Arguments.of(
            new int[][] {
              {0, 0, 0, 0, 0}, //
              {0, 0, 0, 0, 0},
              {0, 0, 0, 0, 0},
              {0, 0, 1, 1, 0},
              {0, 0, 0, 0, 0}
            }, //
            13
            //
            ),
        Arguments.of(
            new int[][] {
              {1, 1, 1, 1, 1, 1}, //
              {1, 1, 1, 1, 1, 1},
              {1, 1, 1, 1, 1, 1},
              {1, 1, 1, 1, 1, 1},
              {1, 1, 1, 1, 1, 1},
              {1, 1, 1, 1, 1, 1}
            }, //
            0
            //
            )
        //
        );
  }
}
