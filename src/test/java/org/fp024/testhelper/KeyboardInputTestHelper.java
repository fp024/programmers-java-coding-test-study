package org.fp024.testhelper;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.junit.jupiter.api.AfterAll;

public class KeyboardInputTestHelper {
  /** 테스트시 System.in의 내용을 변경하므로 일단 원래내용을 백업해둠. */
  private static final InputStream ORIGIN_STDIN = System.in;

  protected static final String ENTER = System.lineSeparator();

  /** 연속적인 테스트는 아니여서, 복구가 필수는 아닌데, 테스트가 끝나면 복구 코드는 추가함. */
  @AfterAll
  static void AfterAll() {
    System.setIn(ORIGIN_STDIN);
  }

  protected static void setKeyboardInput(String keyboardInput) {
    System.setIn(new ByteArrayInputStream((keyboardInput.getBytes())));
  }
}
