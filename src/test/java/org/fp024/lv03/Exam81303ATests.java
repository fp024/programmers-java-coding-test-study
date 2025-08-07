package org.fp024.lv03;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 표 편집 - 이중연결리스트 개념을 더 활용해서 풀어보기!
 *   https://school.programmers.co.kr/learn/courses/30/lessons/81303
 */
@Slf4j
class Exam81303ATests {
  /** 문제 번호 */
  private static final int EXAM_NO = 81303;

  // ========== Target ==========
  static class Solution {
    /** 테이블의 행 */
    static class Row {
      final int rowIndex;
      Row prev, next;

      Row(int rowIndex) {
        this.rowIndex = rowIndex;
      }
    }

    /** 테이블의 행 관리용 이중 연결 리스트 */
    static class DoublyLinkedList {
      Row cursor;
      final int size;

      public DoublyLinkedList(int size, int initCursor) {
        this.size = size;
        cursor = initRows();
        down(initCursor);
      }

      public void up(int moveDistance) {
        for (int i = 0; i < moveDistance; i++) {
          cursor = cursor.prev;
        }
      }

      public void down(int moveDistance) {
        for (int i = 0; i < moveDistance; i++) {
          cursor = cursor.next;
        }
      }

      public void remove() {
        cursor.prev.next = cursor.next;
        cursor.next.prev = cursor.prev;

        if (cursor.next.rowIndex >= size) {
          cursor = cursor.prev;
        } else {
          cursor = cursor.next;
        }
      }

      public void restore(Row restoreRow) {
        restoreRow.prev.next = restoreRow;
        restoreRow.next.prev = restoreRow;
      }

      private Row initRows() {
        Row headDummyRow = new Row(-1);
        Row prevRow = headDummyRow;
        Row currentRow = null;

        for (int i = 0; i < size; i++) {
          currentRow = new Row(i);
          prevRow.next = currentRow;
          currentRow.prev = prevRow;
          prevRow = currentRow;
        }

        Row tailDummy = new Row(size);
        Objects.requireNonNull(currentRow).next = tailDummy;
        tailDummy.prev = currentRow;

        return headDummyRow.next;
      }
    }

    public String solution(int n, int k, String[] cmd) {

      DoublyLinkedList table = new DoublyLinkedList(n, k);
      Deque<Row> stack = new ArrayDeque<>();

      for (String cmdStr : cmd) {
        String[] parts = cmdStr.split(" ");
        String operation = parts[0];
        int moveDistance = 0;
        if (parts.length == 2) {
          moveDistance = Integer.parseInt(parts[1]);
        }

        if ("U".equals(operation)) { // 위로 커스 이동
          table.up(moveDistance);
        } else if ("D".equals(operation)) { // 아래로 커서 이동
          table.down(moveDistance);
        } else if ("C".equals(operation)) { // 현재 선택
          // 삭제 전  Row 객체를 스택에 보관
          stack.push(table.cursor);
          // 연결리스트에서 연결 해제
          table.remove();
        } else if ("Z".equals(operation)) {
          if (!stack.isEmpty()) {
            table.restore(stack.pop());
          }
        }
      }

      StringBuilder answer = new StringBuilder("O".repeat(n));
      while (!stack.isEmpty()) {
        answer.setCharAt(stack.pop().rowIndex, 'X');
      }
      return answer.toString();
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
  //   이제야 처음에 하고자 한대로 된 느낌 😊👍
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
