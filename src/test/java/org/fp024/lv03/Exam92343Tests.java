package org.fp024.lv03;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.lv03.Exam92343Tests.Solution.buildTree;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 양과 늑대
 *   https://school.programmers.co.kr/learn/courses/30/lessons/92343
 */
@Slf4j
class Exam92343Tests {
  /** 문제 번호 */
  private static final int EXAM_NO = 92343;

  // ========== Target ==========
  class Solution {

    static class NodeState {
      private int currentNodeId;
      private int sweepCount;
      private int wolfCount;
      private Set<Integer> childNodeIds = new HashSet<>();
    }

    static int[][] buildTree(int nodeCount, int[][] edges) {

      int[][] tree = new int[nodeCount][];
      Arrays.fill(tree, new int[] {});

      for (int[] edge : edges) {
        if (tree[edge[0]].length == 0) {
          tree[edge[0]] = new int[] {edge[1]};
        } else {
          // 💡 이전 왼쪽 자식만 복사하고 새로운 오른쪽 자식을 추가해서 2개짜리 배열을 새로 만드는데,
          //    문제 제약 조건에서 최대 노드수가 20개도 넘지 않기 때문에, 큰 메모리 낭비는 없을 것 같은데,
          //    일단 이렇게 먼저 해보고 List로 바꿔보도록 하자!
          tree[edge[0]] = new int[] {tree[edge[0]][0], edge[1]};
        }
      }

      return tree;
    }

    public int solution(int[] info, int[][] edges) {
      int answer = 0;
      return answer;
    }
  }

  // ========== Test ==========
  @ParameterizedTest
  @MethodSource({
    "org.fp024.lv03.Exam" + EXAM_NO + "TestData#defaultDataProvider", //
    // "org.fp024.lv03.Exam" + EXAM_NO + "TestData#extraDataProvider"
  })
  void testSolution(int[] info, int[][] edges, int expect) {
    assertThat(new Solution().solution(info, edges)).isEqualTo(expect);
  }

  @ParameterizedTest
  @MethodSource({"org.fp024.lv03.Exam" + EXAM_NO + "TestData#buildTreeTestDataProvider"})
  void testBuildTree(int[] info, int[][] edges, int[][] expect) {
    assertThat(buildTree(info.length, edges)).isEqualTo(expect);
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
