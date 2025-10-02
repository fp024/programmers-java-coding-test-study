package org.fp024.lv02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.lv02.Exam17684Tests.Solution.initDict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * [3차] 압축
 *   https://school.programmers.co.kr/learn/courses/30/lessons/17684
 */
@Slf4j
class Exam17684Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 17684;

  /*
     🤔 ===== 문제 풀이 전략 ===== 🧠
        알고리즘의 구현 방법이 문제에 그대로 나와서,
        그거 그대로 따라 했다.
  */

  // ✨ ========== Target ========== ✨
  static class Solution {

    final Map<String, Integer> dict = initDict();

    // (1) 길이가 1인 모든 단어를 포함하도록 사전을 초기화
    static Map<String, Integer> initDict() {
      Map<String, Integer> dict = new HashMap<>();
      for (char c = 'A'; c <= 'Z'; c++) {
        dict.put(c + "", c - 'A' + 1);
      }
      return dict;
    }

    public int[] solution(String msg) {
      List<Integer> answer = new ArrayList<>();

      for (int i = 0; i < msg.length(); ) {

        // 사전에서 찾은 가장 긴 단어
        StringBuilder foundLongWord = new StringBuilder();

        for (int j = i; j < msg.length(); j++) {
          final var nextChar = Character.toString(msg.charAt(j));

          if (dict.containsKey(foundLongWord + nextChar)) {
            foundLongWord.append(msg.charAt(j));
          } else {
            dict.put(foundLongWord + nextChar, dict.size() + 1);
            break;
          }
        }

        // 💡 처리길이가 1을 넘을 때의 보정을 해줘야함.
        i += foundLongWord.length();
        answer.add(dict.get(foundLongWord.toString()));
      }

      return answer.stream().mapToInt(Integer::intValue).toArray();
    }
  }

  // ✅ ========== Test ========== ✅
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv02.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv02.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String msg, int[] expect) {
    assertThat(new Solution().solution(msg)).isEqualTo(expect);
  }

  @Test
  void testInitDict() {
    Map<String, Integer> dict = initDict();
    log.info(dict.toString());

    assertThat(dict).hasSize('Z' - 'A' + 1);
    assertThat(dict.get("A")).isEqualTo(1);
    assertThat(dict.get("B")).isEqualTo(2);
    assertThat(dict.get("Y")).isEqualTo(25);
    assertThat(dict.get("Z")).isEqualTo(26);
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
