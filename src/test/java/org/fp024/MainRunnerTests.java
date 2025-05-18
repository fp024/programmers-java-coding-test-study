package org.fp024;

import org.fp024.testhelper.KeyboardInputTestHelper;
import org.junit.jupiter.api.Test;

class MainRunnerTests extends KeyboardInputTestHelper {
  @Test
  void testRun() {
    setKeyboardInput(
        """
        6
        1
        -4
        0
        4
        0
        0
        """);
    MainRunner.main(new String[] {"lv00.Exam001"});
  }
}
