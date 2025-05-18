package org.fp024;

import lombok.extern.slf4j.Slf4j;
import org.fp024.common.Runner;

@Slf4j
public class MainRunner {

  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("[실행방법] gradle clean run --args 레벨_패키지.클래스_이름");
      System.out.println("[예제 01] gradle clean run --args lv00.Exam014");
      System.out.println("[예제 02] gradle clean run --args \"lv00.Exam014 args1 args2 args3\"");
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
        runner.run(newArgs);
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
