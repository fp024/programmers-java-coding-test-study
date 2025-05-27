package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * [카카오 인턴] 키패드 누르기
 *   https://school.programmers.co.kr/learn/courses/30/lessons/67256
 */
@Slf4j
class Exam67256Tests {
  /** 문제 번호 */
  private static final String EXAM_NO = "67256";

  // ========== Target ==========
  static class Solution {
    private static final int KEY_STAR = -1;
    private static final int KEY_SHARP = -2;

    static class Coordinate {
      private final int row;
      private final int col;

      public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
      }

      public int getRow() {
        return row;
      }

      public int getCol() {
        return col;
      }

      // 맨해튼 거리 계산 메서드
      public int getDistance(Coordinate other) {
        return Math.abs(this.row - other.row) + Math.abs(this.col - other.col);
      }
    }

    public String solution(int[] numbers, String hand) {

      StringBuilder result = new StringBuilder();

      /*
       키 패드 매핑 좌표 구성

       1(0,0) 2(0,1) 3(0,2)
       4(1,0) 5(1,1) 6(1,2)
       7(2,0) 8(2,1) 9(2,2)
       *(3,0) 0(3,1) #(3,2)

      */
      // 키패드 좌표 매핑 초기화
      Map<Integer, Coordinate> keypad = new HashMap<>();
      keypad.put(1, new Coordinate(0, 0));
      keypad.put(2, new Coordinate(0, 1));
      keypad.put(3, new Coordinate(0, 2));
      keypad.put(4, new Coordinate(1, 0));
      keypad.put(5, new Coordinate(1, 1));
      keypad.put(6, new Coordinate(1, 2));
      keypad.put(7, new Coordinate(2, 0));
      keypad.put(8, new Coordinate(2, 1));
      keypad.put(9, new Coordinate(2, 2));
      keypad.put(0, new Coordinate(3, 1));
      keypad.put(KEY_STAR, new Coordinate(3, 0));
      keypad.put(KEY_SHARP, new Coordinate(3, 2));

      // 초기 손가락 위치
      Coordinate left = keypad.get(KEY_STAR);
      Coordinate right = keypad.get(KEY_SHARP);

      // col이 0이면 왼손만, col이 1이면 두손 모두 가능, col이 2면 오른손만
      for (int number : numbers) {
        var coordinate = keypad.get(number);

        if (coordinate.getCol() == 0) {
          result.append("L");
          left = coordinate;
        } else if (coordinate.getCol() == 2) {
          result.append("R");
          right = coordinate;
        } else {
          var leftDistance = coordinate.getDistance(left);
          var rightDistance = coordinate.getDistance(right);

          if (leftDistance < rightDistance) {
            result.append("L");
            left = coordinate;
          } else if (leftDistance > rightDistance) {
            result.append("R");
            right = coordinate;
          } else { // 거리가 같다면 어떤 손잡이인지의 기준으로 좌표 설정
            if ("left".equals(hand)) {
              result.append("L");
              left = coordinate;
            } else {
              result.append("R");
              right = coordinate;
            }
          }
        }
      }

      return result.toString();
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(int[] numbers, String hand, String expect) {
    assertThat(new Solution().solution(numbers, hand)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   거리 구하는게 내가 아주 취약하긴 한데... 😂
  //   이번 문제는 확실이 나에게는 어렵다. 바로 생각이 안남.. 이제 부터는 책을 읽고 해야할까?
  //   무작정 돌격이 이제는 안 통하는 것 같다. 🥲
  //
  //   그래서 JetBrains AI에게 (코드 없이?😅) 힌트를 호출했는데... "맨해튼 거리계산하기" 개념을 쓴다고 해서
  //   좌표 모델을 Map으로 구성해서 진행을 해봤는데... 기본 테스트로 일단 통과는 했다.
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   다른 분들의 첫번째 풀이가 꽤 맘에 든다. 나의 코드와 개념은 비슷한데 2차원 배열로 잘 해결하셨다. 👍👍
  //
}
