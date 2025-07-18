package org.fp024.lv02;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayDeque;
import java.util.Deque;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 짝지어 제거하기
 *   https://school.programmers.co.kr/learn/courses/30/lessons/12973
 */
@Slf4j
class Exam12973Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 12973;

  /*
     🤔 ===== 문제 풀이 전략 ===== 🧠
     1. 일단 왼쪽 하나를 스택에 넣음
     2. 다음 문자가 스택 TOP과 같으면 pop 하고 다음번 문자 push
        - TOP과 다르면 이번 문자 push하고 다음번 문자 검사
     --
     처음에는 위처럼 생각했는데.. 코드를 작성하면서 단순화 되었다.
     1. 스택이 비거나 스택 top이 현재입력값과 다르면 스택에 넣기
     2. 그게 아니면 스택 top과 새로 넣을 값이 같으므로 스택에서 제거
     3. 스택이 완전히 비었으면 모주 짝이 맞은 것으로 처리
  */

  // ✨ ========== Target ========== ✨
  static class Solution {
    public int solution(String s) {

      Deque<Character> stack = new ArrayDeque<>();

      char[] chars = s.toCharArray();

      for (char c : chars) {
        if (stack.isEmpty() || stack.peek() != c) {
          stack.push(c);
        } else {
          stack.pop();
        }
      }

      return stack.isEmpty() ? 1 : 0;
    }
  }

  // ✅ ========== Test ========== ✅
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv02.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv02.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String s, int expect) {
    assertThat(new Solution().solution(s)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  // TS로는 먼저 풀었었다.
  // https://github.com/fp024/programmers-js-coding-test-study/blob/master/src/ts/lv_2/exam008-12973.ts
  // https://github.com/fp024/programmers-js-coding-test-study/blob/master/src/ts/lv_2/exam008-12973-a.ts
  //
  //   - Deque를 사용할 때.. addLast와 push를 섞어 쓰면 안된다.
  //     addLast는 Deque의 끝에 추가하고, push는 Deque의 앞에 넣는다.
  //
  //   - Stack 클래스는 Vector를 상속받아 구현된 레거시 클래스이다.
  //     Vector는 모든 메서드가 동기화(thread-safe)되어 있어 멀티 스레드 환경에서 안전하지만,
  //     싱글 스레드에서는 성능 오버헤드가 발생한다
  //   - 반면, Deque 인터페이스의 일반적인 구현체인 ArrayDeque는 내부적으로 배열을 기반으로 하며 동기화되어 있음.
  //     따라서 싱글 스레드 환경(메서드 블록 범위 내부)에서는 Stack 대신 ArrayDeque를 사용하는 것이 더 효율적임.
  //     ArrayDeque는 스택(push/pop)으로도, 큐(addLast/removeFirst)로도 유연하게 사용할 수 있다.
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
