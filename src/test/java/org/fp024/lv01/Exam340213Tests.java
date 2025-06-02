package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * [PCCP 기출문제] 1번 / 동영상 재생기
 *   https://school.programmers.co.kr/learn/courses/30/lessons/340213
 */
@Slf4j
class Exam340213Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 340213;

  // ========== Target ==========
  static class Solution {
    public String solution(
        String videoLen, String pos, String opStart, String opEnd, String[] commands) {

      final var videoLenTimestamp = new PlayTimestamp(videoLen);
      final var opStartTimestamp = new PlayTimestamp(opStart);
      final var opEndTimestamp = new PlayTimestamp(opEnd);

      var posTimestamp = new PlayTimestamp(pos);

      // 💡 시작부터 오프닝 영역에 있을 경우 오프닝 끝으로 이등
      if (posTimestamp.isInRange(opStartTimestamp, opEndTimestamp)) {
        posTimestamp = new PlayTimestamp(opEndTimestamp.timeAsSeconds);
      }

      for (String command : commands) {
        if (command.equals("next")) {
          posTimestamp = posTimestamp.next();

          // 현재시간 +10초가 동영상 길이를 넘어가면 동영상 끝으로 이동
          if (posTimestamp.timeAsSeconds > videoLenTimestamp.timeAsSeconds) {
            posTimestamp = new PlayTimestamp(videoLenTimestamp.timeAsSeconds);
          }
        } else if (command.equals("prev")) {
          posTimestamp = posTimestamp.prev();
          // 현재시간 -10초가 음수가 되면 동영상 맨앞으로 이동
          if (posTimestamp.timeAsSeconds < 0) {
            posTimestamp = new PlayTimestamp(0);
          }
        }

        // 💡 명령이 끝난후 현재 위치가 오프닝 영역에 있을 경우 오프닝 끝으로 이등
        if (posTimestamp.isInRange(opStartTimestamp, opEndTimestamp)) {
          posTimestamp = new PlayTimestamp(opEndTimestamp.timeAsSeconds);
        }
      }

      return posTimestamp.toString();
    }

    /** 재생 시간 타임 스템프 */
    static class PlayTimestamp {
      private static final int MOVE_TIME_SECOND = 10;
      // 초단위 시간
      final int timeAsSeconds;

      PlayTimestamp(int timeAsSeconds) {
        this.timeAsSeconds = timeAsSeconds;
      }

      PlayTimestamp(String stringTimeAsSeconds) {
        this.timeAsSeconds = stringTimeToSecond(stringTimeAsSeconds);
      }

      static int stringTimeToSecond(String minutesAndSeconds) {
        var minutesAndSecondsArray = minutesAndSeconds.split(":");
        var minutes = Integer.parseInt(minutesAndSecondsArray[0]);
        var second = Integer.parseInt(minutesAndSecondsArray[1]);
        return minutes * 60 + second;
      }

      static String secondsToString(int seconds) {
        var minutes = seconds / 60;
        var second = seconds % 60;
        return String.format("%02d:%02d", minutes, second);
      }

      int prevTime() {
        return timeAsSeconds - MOVE_TIME_SECOND;
      }

      PlayTimestamp prev() {
        return new PlayTimestamp(prevTime());
      }

      int nextTime() {
        return timeAsSeconds + MOVE_TIME_SECOND;
      }

      PlayTimestamp next() {
        return new PlayTimestamp(nextTime());
      }

      boolean isInRange(PlayTimestamp start, PlayTimestamp end) {
        return this.timeAsSeconds >= start.timeAsSeconds && this.timeAsSeconds <= end.timeAsSeconds;
      }

      @Override
      public String toString() {
        return secondsToString(this.timeAsSeconds);
      }
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(
      String videoLen, String pos, String opStart, String opEnd, String[] commands, String expect) {
    assertThat(new Solution().solution(videoLen, pos, opStart, opEnd, commands)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   플레이 시간 타임 스템프 클래스를 만들어서 그다지 어렵지 않게 끝낸 것 같은데...
  //
  //   오프닝에 대해서 약간 이해가 부족했는데...
  //   오프닝 영역에 들어오면 오프닝 끝시간으로 보내야 되는 것 같다.
  //   +1점 통과 😅
  //   내가 문제를 대충 읽었나보다..😂😂😂 빨리 풀렸다고 생각했는데, 시간이 더걸림.
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
