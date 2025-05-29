package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 유연근무제
 *   https://school.programmers.co.kr/learn/courses/30/lessons/388351
 */
@Slf4j
class Exam388351Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 388351;

  // ========== Target ==========
  static class Solution {
    public int solution(int[] schedules, int[][] timeLogs, int startDay) {
      int answer = 0;

      int[] latenessCounts = new int[schedules.length];

      for (int employeeIndex = 0; employeeIndex < timeLogs.length; employeeIndex++) {
        for (int dayIndex = 0; dayIndex < timeLogs[employeeIndex].length; dayIndex++) {
          // 사원이 정의한 출근시간 + 10분의 유예 시간
          var scheduledTimeWithGrace = new WorkStartTime(schedules[employeeIndex], 10);

          // 실제 출근한 시간
          var actualArrivalTime = new WorkStartTime(timeLogs[employeeIndex][dayIndex]);

          // 주말이 아니고, 도착 시간이 출근 정의 시간보다 크면 해당 사원의 지각 카운트 증가
          if (!isWeekend(startDay, dayIndex)
              && actualArrivalTime.compareTo(scheduledTimeWithGrace) > 0) {
            latenessCounts[employeeIndex]++;
          }
        }
      }

      for (int latenessCount : latenessCounts) {
        if (latenessCount == 0) {
          answer++;
        }
      }

      return answer;
    }

    /** 주말 여부 체크 */
    static boolean isWeekend(int startDay, int index) {
      final int DAYS_IN_WEEK = 7;
      int dayOfWeek = (startDay + index) % DAYS_IN_WEEK;
      // 토요일(6)과 일요일(0)이 주말
      return dayOfWeek == 6 || dayOfWeek == 0;
    }

    /** 출근 시간 클래스 */
    static class WorkStartTime implements Comparable<WorkStartTime> {
      final int hour;
      final int minute;

      WorkStartTime(int timeInHHMM) {
        this.hour = timeInHHMM / 100;
        this.minute = timeInHHMM % 100;
      }

      /** 생성할 때, 유예 시간(minutes)을 더해서 생성 */
      WorkStartTime(int timeInHHMM, int plusMinutes) {
        var hour = timeInHHMM / 100;
        var minute = timeInHHMM % 100;

        var totalMinutes = hour * 60 + minute + plusMinutes;
        this.hour = totalMinutes / 60;
        this.minute = totalMinutes % 60;
      }

      @Override
      public int compareTo(WorkStartTime other) {
        if (this.hour < other.hour) {
          return -1;
        } else if (this.hour > other.hour) {
          return 1;
        } else {
          return Integer.compare(this.minute, other.minute);
        }
      }
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(int[] schedules, int[][] timeLogs, int startDay, int expect) {
    assertThat(new Solution().solution(schedules, timeLogs, startDay)).isEqualTo(expect);
  }

  @Test
  void testWorkStartTime() {
    var aTime = new Solution.WorkStartTime(600);
    var bTime = new Solution.WorkStartTime(710);

    assertThat(aTime.hour).isEqualTo(6);
    assertThat(aTime.minute).isEqualTo(0);

    assertThat(bTime.hour).isEqualTo(7);
    assertThat(bTime.minute).isEqualTo(10);

    assertThat(aTime.compareTo(bTime)).isEqualTo(-1);
  }

  @Test
  void testIsWeekend() {
    assertThat(Solution.isWeekend(5, 0)).isFalse();
    assertThat(Solution.isWeekend(5, 1)).isTrue();
    assertThat(Solution.isWeekend(5, 2)).isTrue();
    assertThat(Solution.isWeekend(5, 3)).isFalse();
    assertThat(Solution.isWeekend(5, 4)).isFalse();
    assertThat(Solution.isWeekend(5, 5)).isFalse();
    assertThat(Solution.isWeekend(5, 6)).isFalse();
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   이번에는 출근 시간에 대한 클래스 모델도 만들어보고 내부 유틸리티 메서드에 대한
  //   테스트 검증도 별도로 해보았는데, 문제가 깔끔하게 풀렸다.
  //   프로그래머스 제출전에, JetBrains AI에게 변수명 추천을 부탁했는데,
  //   아무 의미있게 좋게 바뀌었다. 👍
  //   +1점 획득
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   첫번째 스트림 전문가 분들은 진짜 짧은 코드를 만들어내심. 😅
  //
}
