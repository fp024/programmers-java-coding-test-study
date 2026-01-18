package org.fp024.lv03;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 섬 연결하기
 *   https://school.programmers.co.kr/learn/courses/30/lessons/42681
 */
@Slf4j
class Exam42681Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 42681;

  // ========== Target ==========
  static class Solution {

    /**
     * 부모노드 번호 찾기
     *
     * @param parents 부모 노드를 나타내는 배열
     * @param node 부모노드를 찾을 노드번호
     * @return 부모 노드 번호
     */
    private int find(int[] parents, int node) {
      if (parents[node] == node) {
        return node;
      }

      parents[node] = find(parents, parents[node]);
      return parents[node];
    }

    /**
     * 유니온 - 집합 합치기
     *
     * <p>일단은... 랭크를 고려하지 않음 😅.
     *
     * @param parents 부모 노드를 나타내는 배열
     * @param a a노드
     * @param b b노드
     */
    private void union(int[] parents, int a, int b) {
      int aRoot = find(parents, a);
      int bRoot = find(parents, b);
      if (aRoot != bRoot) {
        parents[aRoot] = bRoot;
      }
    }

    public int solution(int n, int[][] costs) {

      // 각 노드의 부모는 자기자신, 자신이 루트
      int[] parents = new int[n];
      for (int i = 0; i < n; i++) {
        parents[i] = i;
      }

      // 건설 비용 기준으로 오름차순
      Arrays.sort(costs, Comparator.comparingInt(a -> a[2]));

      int minCost = 0;
      int edgeCount = 0;

      for (int[] cost : costs) {
        if (edgeCount == n - 1) {
          break;
        }

        int aNode = find(parents, cost[0]);
        int bNode = find(parents, cost[1]);
        var abCost = cost[2];

        // 루트가 같으면 사이클이므로 그냥 넘김
        if (aNode == bNode) {
          continue;
        }

        union(parents, aNode, bNode);
        minCost += abCost;
        edgeCount++;
      }

      return minCost;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv03.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv03.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(int n, int[][] costs, int expect) {
    assertThat(new Solution().solution(n, costs)).isEqualTo(expect);
  }

  // cspell:enable
  //
  // === 문제 읽고 첫 느낌 ===
  //   랭크에 대한 이해는 아직 부족해서, 해당 개념은 적용하지 않고 풀었다. 😂
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
