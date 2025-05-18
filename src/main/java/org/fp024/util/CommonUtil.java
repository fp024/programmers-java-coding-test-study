package org.fp024.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 공통 유틸리티 클래스
 *
 * <p>공통적으로 사용하게 될만한 메서드를 모아두자!
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtil {
  /** 2차원 배열을 화면에 출력 */
  public static String print2dArray(int[][] array) {
    StringBuilder result = new StringBuilder();
    for (int[] a : array) {
      for (int anInt : a) {
        result.append(anInt).append(" ");
      }
      result.append(System.lineSeparator());
    }
    return result.toString();
  }

  /**
   * 배열의 i, j 인덱스에 해당하는 값 Swap
   *
   * @param A 배열
   * @param i i 인덱스
   * @param j j 인덱스
   */
  public static void swap(int[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }

  public static void swap(char[] A, int i, int j) {
    char temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }
}
