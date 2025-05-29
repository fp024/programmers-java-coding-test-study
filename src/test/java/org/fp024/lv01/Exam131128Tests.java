package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.TreeMap;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 숫자 짝꿍
 *   https://school.programmers.co.kr/learn/courses/30/lessons/131128
 */
@Slf4j
class Exam131128Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 131128;

  // ========== Target ==========
  static class Solution {
    public String solution(String x, String y) {
      String answer;
      StringBuilder result = new StringBuilder();

      // 1. 문자, 카운트의 트리맵 생성, 순서는 문자가 큰 순서를 적용
      TreeMap<Character, Integer> xMap = new TreeMap<>((o1, o2) -> o2 - o1);
      TreeMap<Character, Integer> yMap = new TreeMap<>((o1, o2) -> o2 - o1);

      for (int i = 0; i < x.length(); i++) {
        xMap.put(x.charAt(i), xMap.getOrDefault(x.charAt(i), 0) + 1);
      }

      for (int i = 0; i < y.length(); i++) {
        yMap.put(y.charAt(i), yMap.getOrDefault(y.charAt(i), 0) + 1);
      }

      // 2. 작은 맵을 기준으로 찾아도 되긴 되는데... 키가 0~9까지 밖에 없어서 아무거나 먼저 해도 될 것 같다.
      for (var xe : xMap.entrySet()) {
        Character xKey = xe.getKey();
        Integer xValue = xe.getValue();
        int sameCount = 0;

        if (yMap.containsKey(xKey)) {
          Integer yValue = yMap.get(xKey);
          sameCount = Math.min(xValue, yValue);
        }

        // 💡repeat를 쓰면 편한데...😅 Java 21부터 있다. 😂
        // result.repeat(xKey, sameCount);
        for (int i = 0; i < sameCount; i++) {
          result.append(xKey);
        }
      }

      // 💡 StringBuilder의 isEmpty() 메서드는 Java 15부터 지원한다.
      if (result.length() > 0) {
        answer = result.toString();
        if (result.charAt(0) == '0') {
          answer = "0";
        }
      } else {
        answer = "-1";
      }

      return answer;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv01.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv01.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(String x, String y, String expect) {
    assertThat(new Solution().solution(x, y)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   그렇게 어려운 문제는 아닐 것 같았는데, 좀 시간이 걸렸다.
  //   `.collect(Collectors.toList());`는 변경 가능 리스트
  //   `toList();`는 불변 리스트를 반환의 차이가 있었구나...
  //    불변으로 하면 remove 및 내용 변경(set)도 안된다.
  //
  //    아예 다른 방법을 생각한다면..
  //    1. TreeMap에다 0 ~ 9의 갯수를 넣는다.
  //       key 숫자 value 번호
  //       결국 x, y에 대한 숫자갯수를 세어둔 TreeMap 2개가 만듬.
  //
  //    2. 동시에 존재해야하기 때문에 둘중 어느것을 먼저 세도 상관은 없어보이고,
  //       큰 숫자부터 두 treeMap 몇개를 들고 있나 검사하면서 숫자를 만들어내면 될 것 같다. 😅
  //
  //     TreeMap으로 바꾸고도 또 틀렸는데...
  //     x,y가 둘다 일치한다면 300만 자리의 수가 되므로 이걸 Integer로 바꿔보려해서 문제가 생긴게 맞는 것 같다.
  //     그대로 출력하고 모든 수가 0으로 이루어졌을 때..만 0으로 잘 반환하게 처리하면 될 것 같다.
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //    결국 통과 했다. +1점 얻음 😅
  //    그런데 0~9의 카운를 셀 때.. TreeMap까지 사용하지 않고, 배열로 처리해도 되었던 것 같긴하다.
  //    잘하시는 분들 배열로 처리한 것 같음...
  //
}
