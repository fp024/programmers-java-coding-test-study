package org.fp024.common;

/**
 * 실행 인터페이스
 *
 * @param <R> 결과 타입
 */
public interface Runner<R> {

  /**
   * 실행
   *
   * @param args 파라미터
   * @return 실행 결과
   * @throws Exception 예외
   */
  R run(String[] args) throws Exception;
}
