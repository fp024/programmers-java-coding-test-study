package org.fp024.practice;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class AntSequenceTests {

  private final AntSequence antSequence = new AntSequence();

  @Test
  void testAntSequence() {
    assertThat(antSequence.solution(1)).isEqualTo("1");
    assertThat(antSequence.solution(2)).isEqualTo("11");
    assertThat(antSequence.solution(3)).isEqualTo("12");
    assertThat(antSequence.solution(4)).isEqualTo("1121");
    assertThat(antSequence.solution(5)).isEqualTo("122111");
    assertThat(antSequence.solution(6)).isEqualTo("112213");
  }
}
