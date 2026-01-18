package org.fp024.lv03;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 섬 연결하기 - ✨ 랭크 적용
 *   https://school.programmers.co.kr/learn/courses/30/lessons/42681
 */
@Slf4j
class Exam42681ATests {
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
     * <p>랭크(Rank) 기반의 최적화 적용
     *
     * @param parents 부모 노드를 나타내는 배열
     * @param ranks 랭크 정보 배열
     * @param a a노드
     * @param b b노드
     */
    private void union(int[] parents, int[] ranks, int a, int b) {
      int aRoot = find(parents, a);
      int bRoot = find(parents, b);

      if (aRoot == bRoot) {
        return;
      }

      // b의 랭크가 높으면 a의 루트를 b로 한다.
      if (ranks[aRoot] < ranks[bRoot]) {
        parents[aRoot] = bRoot;
      } else if (ranks[aRoot] > ranks[bRoot]) {
        parents[bRoot] = aRoot;
      } else { // a, b의 랭크가 같으면 한쪽을 다른 쪽에 붙이고, 루트가 된 쪽의 랭크를 1 증가시킨다.
        parents[aRoot] = bRoot;
        ranks[bRoot]++;
      }
    }

    public int solution(int n, int[][] costs) {

      // 각 노드의 부모는 자기자신, 자신이 루트
      int[] parents = new int[n];
      for (int i = 0; i < n; i++) {
        parents[i] = i;
      }

      // 랭크 초기화
      int[] ranks = new int[n];

      // 건설 비용 기준으로 오름차순
      Arrays.sort(costs, Comparator.comparingInt(a -> a[2]));

      int minCost = 0;
      int edgeCount = 0;

      for (int[] cost : costs) {
        if (edgeCount == n - 1) {
          break;
        }

        int aRoot = find(parents, cost[0]);
        int bRoot = find(parents, cost[1]);
        int abCost = cost[2];

        // 루트가 같으면 사이클이므로 그냥 넘김
        if (aRoot == bRoot) {
          continue;
        }

        union(parents, ranks, aRoot, bRoot);
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
  //   랭크 적용, 좀 이해가 되는 느낌..😊
  //
  // === 다른 사람 풀이 확인 이후 의견 ===
  // ...
  //
}
