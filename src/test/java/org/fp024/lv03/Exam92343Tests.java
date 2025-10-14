package org.fp024.lv03;

import static org.assertj.core.api.Assertions.assertThat;
import static org.fp024.lv03.Exam92343Tests.Solution.buildTree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
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
  static class Solution {

    /** 노드 상태 */
    private static class NodeState {
      /** 현재 방문한 Node ID */
      final int currentNodeId;

      /** 현재까지 누적된 양(🐑)의 수 */
      final int cumulativeSheepCount;

      /** 현재까지 누적된 늑대(🐺) 수 */
      final int cumulativeWolfCount;

      final Set<Integer> nextNodeIds;

      public NodeState(
          int currentNodeId,
          int cumulativeSheepCount,
          int cumulativeWolfCount,
          Set<Integer> nextNodeIds) {
        this.currentNodeId = currentNodeId;
        this.cumulativeSheepCount = cumulativeSheepCount;
        this.cumulativeWolfCount = cumulativeWolfCount;
        this.nextNodeIds = nextNodeIds;
      }
    }

    static int[][] buildTree(int nodeCount, int[][] edges) {

      int[][] tree = new int[nodeCount][];

      // 한개의 빈 배열을 모든 요소가 참조하지만,
      // 값을 갱신할 때마다 배열을 새로 생성해서 재할당해서 문제는 없다.
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

    /**
     * 문제 해결 함수
     *
     * <p>트리 구조에서 게임 규칙에 따라 늑대를 피하면서 안전하게 모을 수 있는 양의 최대 개수를 계산
     *
     * @param info 각 요소가 트리의 노드를 나타내는 배열. 값 0은 양을, 값 1은 늑대를 나타냄.
     * @param edges 트리의 간선을 나타내는 2차원 배열. <br>
     *     각 간선은 부모 노드와 자식 노드 간의 연결을 나타내는 정수 쌍 [parent, child]로 정의됨.
     * @return 주어진 규칙에 따라 모을 수 있는 양의 최대 개수.
     */
    public int solution(int[] info, int[][] edges) {
      int[][] tree = buildTree(info.length, edges);

      int maxSheepCount = 0;

      Queue<NodeState> queue = new ArrayDeque<>();

      // 루트 노드 설정
      queue.add(new NodeState(0, 1, 0, new HashSet<>()));

      while (!queue.isEmpty()) {
        // 큐에서 현재 상태 가져오기
        NodeState currentStatus = queue.poll();

        // 누적된 양(🐑)의 수 업데이트
        maxSheepCount = Math.max(maxSheepCount, currentStatus.cumulativeSheepCount);

        for (int nextNodeId : tree[currentStatus.currentNodeId]) {
          currentStatus.nextNodeIds.add(nextNodeId);
        }

        for (int nextNodeId : currentStatus.nextNodeIds) {
          // 다음 탐색 노드가 늑대(🐺)인 경우
          if (info[nextNodeId] == 1) {

            int nextWolfCount = currentStatus.cumulativeWolfCount + 1;

            // 양(🐑)이 늑대(🐺)보다 많을 때만 계속 탐색 가능
            if (currentStatus.cumulativeSheepCount > nextWolfCount) {
              var newNextNodeIds = new HashSet<>(currentStatus.nextNodeIds);
              newNextNodeIds.remove(nextNodeId); // 방문한 노드는 제거
              queue.add(
                  new NodeState(
                      nextNodeId, //
                      currentStatus.cumulativeSheepCount,
                      nextWolfCount, // 늑대(🐺)도 항상 누적됨 (단, 늑대(🐺)가 양(🐑) 이상이 되면 게임오버)
                      newNextNodeIds));
            }
          } // 다음 탐색 노드가 양(🐑)인 경우
          else {
            var newNextNodeIds = new HashSet<>(currentStatus.nextNodeIds);
            newNextNodeIds.remove(nextNodeId); // 방문한 노드는 제거
            queue.add(
                new NodeState(
                    nextNodeId, //
                    currentStatus.cumulativeSheepCount + 1,
                    currentStatus.cumulativeWolfCount,
                    newNextNodeIds));
          }
        }
      }

      return maxSheepCount;
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
