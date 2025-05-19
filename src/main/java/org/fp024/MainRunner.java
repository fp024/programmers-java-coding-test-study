package org.fp024;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.fp024.common.Runner;

@Slf4j
public class MainRunner {

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("[실행방법] gradle clean run --args 레벨_패키지.클래스_이름");
      System.out.println("[예제 01] gradle clean run --args lv00.Exam001Sample");
      System.out.println("  Exam001Sample 예제는 Scanner 입력을 받으므로...");
      System.out.println("  숫자 들을 엔터로 구분해서 입력 후, 입력이 끝나면 Ctrl+Z 입력으로 끝냄");
      return;
    }

    String[] newArgs = null;
    if (args.length > 1) {
      newArgs = new String[args.length - 1];
      System.arraycopy(args, 1, newArgs, 0, newArgs.length);
    }

    final var className = args[0];
    try {
      Class<?> clazz = Class.forName("org.fp024.%s".formatted(className));

      var object = clazz.getConstructor().newInstance();

      if (object instanceof Runner<?> runner) {
        System.out.printf("=== %s 결과 출력 ===%n", runner.getClass().getSimpleName());
        Object result = runner.run(newArgs);
        if (result != null && result.getClass().isArray()) {
          switch (result) {
            case int[] i -> System.out.println(Arrays.toString(i));
            case long[] l -> System.out.println(Arrays.toString(l));
            case double[] d -> System.out.println(Arrays.toString(d));
            case boolean[] b -> System.out.println(Arrays.toString(b));
            case char[] c -> System.out.println(Arrays.toString(c));
            default -> System.out.println(Arrays.toString((Object[]) result));
          }
        } else {
          System.out.println(result);
        }
      } else {
        System.out.printf("[오류] %s 문제 클래스는 Runner를 구현하지 않았습니다.", object.getClass().getName());
      }
    } catch (ClassNotFoundException e) {
      System.out.printf("[오류] org.fp024.%s 문제 클래스를 찾을 수 없습니다.%n", className);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      System.out.printf("[오류] %s, 에러 발생, 로그를 확인하세요.%n", e.getMessage());
    }
  }
}
