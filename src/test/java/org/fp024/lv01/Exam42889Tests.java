package org.fp024.lv01;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 실패율 - 42889
 *   https://school.programmers.co.kr/learn/courses/30/lessons/42889
 */
@Slf4j
class Exam42889Tests {
  static class Solution {
    public int[] solution(int N, int[] stages) {

      // 스테이지, 실패율 맵
      Map<Integer, Double> map = new HashMap<>();

      for (int s = 1; s <= N; s++) {
        map.put(s, failRate(s, stages));
      }

      List<Map.Entry<Integer, Double>> entryList = new ArrayList<>(map.entrySet());
      entryList.sort(
          (o1, o2) -> {
            if (o2.getValue().compareTo(o1.getValue()) == 0) {
              return o1.getKey() - o2.getKey();
            }
            return o2.getValue().compareTo(o1.getValue());
          });

      System.out.println(entryList);

      return entryList.stream().mapToInt(Map.Entry::getKey).toArray();
    }

    /** 실패율 계산 */
    private Double failRate(int s, int[] stages) {
      int failCount = 0;
      int challenger = 0;

      for (int stage : stages) {
        if (stage == s) {
          failCount++;
        }
        if (stage >= s) {
          challenger++;
        }
      }
      if (challenger == 0) {
        return 0.0;
      }
      return failCount / (double) challenger;
    }
  }

  @ParameterizedTest
  @MethodSource({"defaultDataProvider", "extraDataProvider"})
  void testSolution(int N, int[] stages, int[] expect) {
    assertThat(new Solution().solution(N, stages)).isEqualTo(expect);
  }

  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            5, //
            new int[] {2, 1, 2, 6, 2, 4, 3, 3},
            new int[] {3, 4, 2, 1, 5}
            //
            ),
        Arguments.of(
            4, //
            new int[] {4, 4, 4, 4, 4},
            new int[] {4, 1, 2, 3}
            //
            )
        //
        );
  }

  static Stream<Arguments> extraDataProvider() {
    return Stream.of(
        // 총 스테이지가 2인데, 스테이지 2에 도달 못 한 경우
        Arguments.of(
            2, //
            new int[] {1, 1},
            new int[] {1, 2}
            //
            )
        //
        );
  }
  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   문제에 나온 실패율 공식에 따라 풀었는데...
  //   JS/TS라면 map을 값을 보고 아주 쉬웠을 텐데, Java에서는 다른 방법이 있을지는 모르겠으나..
  //   Map을 List로 바꾼 다음에 Comparator 지정해서 결과를 얻었다.
  //   +2점 얻음.
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  //   스테이지 자체를 클레스로 하시는 분들이 꽤 보인다. 👍
  //
}
