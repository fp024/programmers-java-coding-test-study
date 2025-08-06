package org.fp024.lv03;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Deque;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 표 편집
 *   https://school.programmers.co.kr/learn/courses/30/lessons/81303
 */
@Slf4j
class Exam81303Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 81303;

  // ========== Target ==========
  static class Solution {
    static class Row {
      int rowIndex;
      Row prev;
      Row next;

      public void remove() {
        prev.next = next;
        next.prev = prev;
      }

      public void restore() {
        prev.next = this;
        next.prev = this;
      }
    }

    public String solution(int n, int k, String[] cmd) {
      // (1) 표 정의
      Row[] table = new Row[n + 2];

      // (2) 표의 첫 더미 행 설정
      Row dummyRow = new Row();
      dummyRow.rowIndex = 0;
      table[0] = dummyRow;

      // (3) 다음 열들을 줄줄이 설정해서, 표의 행들을 연결리스트 관계 완성
      //     표의 0번과 n번 행은 가상 Row로 설정
      for (int i = 1; i < n + 2; i++) {
        Row prevRow = table[i - 1];
        Row currentRow = new Row();
        currentRow.rowIndex = i;
        currentRow.prev = prevRow;

        table[i] = currentRow;
        prevRow.next = currentRow;
      }

      // (4) 현재 커서 위치 설정
      var cursor = k + 1;

      // (5) 삭제한 row의 RowIdex 스텍에 저장
      Deque<Integer> stack = new ArrayDeque<>();

      for (String cmdStr : cmd) {
        var rawCommand = cmdStr.split(" ");
        var command = rawCommand[0];
        int moveDistance = 0;
        if (rawCommand.length == 2) {
          moveDistance = Integer.parseInt(rawCommand[1]);
        }

        if ("U".equals(command)) { // 위로 커스 이동
          Row current = table[cursor];
          for (int i = 0; i < moveDistance; i++) {
            current = current.prev;
          }
          cursor = current.rowIndex;

        } else if ("D".equals(command)) { // 아래로 커서 이동
          Row current = table[cursor];
          for (int i = 0; i < moveDistance; i++) {
            current = current.next;
          }

          cursor = current.rowIndex;

        } else if ("C".equals(command)) { // 현재 선택
          Row current = table[cursor];
          Row prevRow = current.prev;
          Row nextRow = current.next;

          current.remove();

          // 새로운 커서 위치 정의
          if (n < nextRow.rowIndex) { // 마지막 더미 행이면
            cursor = prevRow.rowIndex;
          } else {
            cursor = nextRow.rowIndex;
          }
          // 스택에 삭제한 rowIndex 보관
          stack.push(current.rowIndex);

        } else if ("Z".equals(command)) {
          if (stack.peek() != null) {
            table[stack.pop()].restore();
          }
        }
      }

      StringBuilder sb = new StringBuilder("O".repeat(n));

      while (!stack.isEmpty()) {
        sb.setCharAt(stack.pop() - 1, 'X');
      }

      return sb.toString();
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv03.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv03.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(int n, int k, String[] cmd, String expect) {
    assertThat(new Solution().solution(n, k, cmd)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  // ...
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
