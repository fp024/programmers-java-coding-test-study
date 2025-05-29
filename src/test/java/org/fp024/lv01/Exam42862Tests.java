package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 체육복
 *   https://school.programmers.co.kr/learn/courses/30/lessons/42862
 */
@Slf4j
class Exam42862Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 42862;

  // ========== Target ==========
  static class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
      int answer = 0;

      List<Student> students = new ArrayList<>();

      // 1. 체육복을 하나씩 들고 있던 모든 학생
      for (int i = 0; i < n; i++) {
        Student student = new Student();
        student.id = i + 1;
        student.gymClothes = 1;
        students.add(student);
      }

      // 2. 몇몇 학생이 잃어버림 😂
      for (int lostId : lost) {
        students.get(lostId - 1).gymClothes = 0;
      }

      // 3. 그런데 몇몇 학생은 여벌을 한벌씩 가져왔음 👍
      for (int reserveId : reserve) {
        students.get(reserveId - 1).gymClothes++;
      }

      // 4. 여벌 체육복 서로 나눠주기
      for (int i = 0; i < n; i++) {

        if (students.get(i).gymClothes == 0) {
          if (i - 1 > -1 //
              && students.get(i - 1).gymClothes > 1) {
            students.get(i - 1).gymClothes = students.get(i - 1).gymClothes - 1;
            students.get(i).gymClothes++;
          } else if (i + 1 < n //
              && students.get(i + 1).gymClothes > 1) {
            students.get(i + 1).gymClothes = students.get(i + 1).gymClothes - 1;
            students.get(i).gymClothes++;
          }
        }
      }

      // 5. 체육복을 입을 수 있는 학생 총원 세기
      answer =
          students.stream().map(student -> student.gymClothes > 0 ? 1 : 0).reduce(0, Integer::sum);

      return answer;
    }

    static class Student {
      int id;
      int gymClothes;

      @Override
      public String toString() {
        return Integer.toString(gymClothes);
      }
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(int n, int[] lost, int[] reserve, int expect) {
    assertThat(new Solution().solution(n, lost, reserve)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   이것도 직전에 풀은 문제와 비슷하게 Map을 사용해볼까?
  //   결국 맵대신 학생 도메인을 만들고, 직전 문제 스타일로 풀어봤는데,
  //   +3점은 나왔다. 👍
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   비슷비슷한것 같다...
  //
}
