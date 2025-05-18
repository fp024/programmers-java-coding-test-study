package org.fp024.lv00;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 코딩 테스트 입문 05
 * https://school.programmers.co.kr/learn/challenges/beginner?order=acceptance_desc
 *
 * 3개 문제 남긴 했는데... 점점 어려워져서 일찍 클래스를 분리했다. 😂
 */
@Slf4j
class BeginnerExam05Tests {

  /*
   * 💡💡💡💡 평행
   *     https://school.programmers.co.kr/learn/courses/30/lessons/120875
   *
   * 힌트: https://school.programmers.co.kr/questions/52363
   *      https://school.programmers.co.kr/questions/47406
   *      https://velog.io/@shin75492/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4JAVA-%ED%8F%89%ED%96%89
   *
   */
  @Disabled("최초 생각했으나 틀림")
  @ParameterizedTest
  @MethodSource("test_120875_dataProvider")
  void test_120876(int[][] dots, int expect) {
    double s01 = slope(dots[0], dots[1]);
    double s02 = slope(dots[0], dots[2]);

    double s03 = slope(dots[0], dots[3]);
    double s04 = slope(dots[1], dots[2]);

    double s05 = slope(dots[1], dots[3]);
    double s06 = slope(dots[2], dots[3]);

    System.out.println("s01 = " + s01);
    System.out.println("s02 = " + s02);
    System.out.println("s03 = " + s03);
    System.out.println("s04 = " + s04);
    System.out.println("s05 = " + s05);
    System.out.println("s06 = " + s06);

    Set<String> set = new HashSet<>();
    set.add(Double.toString(s01));
    set.add(Double.toString(s02));
    set.add(Double.toString(s03));
    set.add(Double.toString(s04));
    set.add(Double.toString(s05));
    set.add(Double.toString(s06));

    System.out.println("set = " + set);

    int answer = set.size() < 6 ? 1 : 0;
    System.out.println("answer = " + answer);
    assertThat(answer).isEqualTo(expect);
  }

  /** 기울기 계산 메서드 */
  double slope(int[] p1, int[] p2) {
    return (double) (p1[1] - p2[1]) / (p1[0] - p2[0]);
  }

  static Stream<Arguments> test_120875_dataProvider() {
    return Stream.of(
        Arguments.of(
            new int[][] {
              {1, 4},
              {9, 2},
              {3, 8},
              {11, 6}
            },
            1
            //
            ),
        Arguments.of(
            new int[][] {
              {3, 5},
              {4, 1},
              {2, 4},
              {5, 10}
            },
            0
            //
            ),
        Arguments.of(
            new int[][] {
              {3, 5},
              {4, 1},
              {3, 5},
              {4, 1}
            },
            1
            //
            ),
        Arguments.of(
            new int[][] {
              {1, 2},
              {2, 1},
              {3, 4},
              {4, 5}
            },
            0
            //
            )
        //
        );
  }

  /** 확실히 다른 좌표 쌍이 평행일 때 1로 설정해서 맞게 되는 것 같다. */
  @ParameterizedTest
  @MethodSource("test_120875_dataProvider")
  void test_120876_2nd(int[][] dots, int expect) {
    int answer = 0;
    // 💡 한번 검사할 때마다 좌표 중복이 없어야한다.
    double s01 = slope(dots[0], dots[1]);
    double s06 = slope(dots[2], dots[3]);

    if (s01 == s06) {
      answer = 1;
    }

    double s02 = slope(dots[0], dots[2]);
    double s05 = slope(dots[1], dots[3]);

    if (s02 == s05) {
      answer = 1;
    }

    double s04 = slope(dots[1], dots[2]);
    double s03 = slope(dots[0], dots[3]);

    if (s04 == s03) {
      answer = 1;
    }

    assertThat(answer).isEqualTo(expect);
  }
}
