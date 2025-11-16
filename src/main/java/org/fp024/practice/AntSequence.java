package org.fp024.practice;

/**
 * 개미 수열
 *
 * <p>첫 번째 수열: 1 두 번째 수열: 11 세 번째 수열: 12 네 번째 수열: 1121 다섯 번째 수열: 122111 여섯 번째 수열: 112113 ... 수열의 구성:
 * {번호}{카운트}...
 */
public class AntSequence {

  /**
   * n번째 개미 수열을 출력
   *
   * @param n 몇번 째
   * @return 개미수열 문자열
   */
  public String solution(int n) {

    // 초기 값: 1
    var currentSequence = "1";

    for (var term = 2; term <= n; term++) {

      var nextSequence = new StringBuilder();

      // 내부 루프: 현재 currentSequence를 읽어서 다음 항 생성
      var i = 0;
      while (i < currentSequence.length()) {
        var currentChar = currentSequence.charAt(i);
        var count = 0;

        while (i < currentSequence.length() && currentSequence.charAt(i) == currentChar) {
          count++;
          i++;
        }

        // "{숫자}{개수}" 순서로 추가
        nextSequence.append(currentChar).append(count);
      }
      currentSequence = nextSequence.toString();
    }

    return currentSequence;
  }
}
