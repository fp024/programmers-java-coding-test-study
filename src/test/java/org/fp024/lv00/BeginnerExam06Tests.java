package org.fp024.lv00;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 코딩 테스트 입문 06
 * https://school.programmers.co.kr/learn/challenges/beginner?order=acceptance_desc
 *
 * 1개 문제 남긴 했는데... 점점 어려워져서 일찍 클래스를 분리했다. 😂
 */
@Slf4j
class BeginnerExam06Tests {

  /*
   * 옹알이 (1)
   *     https://school.programmers.co.kr/learn/courses/30/lessons/120956
   *
   * 힌트:
   *
   */
  @ParameterizedTest
  @MethodSource("test_120956_dataProvider")
  void test_120956(String[] babbling, int expect) {
    int answer = 0;

    Pattern pattern = Pattern.compile("aya|ye|woo|ma");

    for (int i = 0; i < babbling.length; i++) {
      Matcher matcher = pattern.matcher(babbling[i]);
      while (matcher.find()) {
        babbling[i] = babbling[i].replaceFirst(matcher.group(0), "");
      }
    }

    for (int i = 0; i < babbling.length; i++) {
      if ("".equals(babbling[i])) {
        answer++;
      }
    }

    assertThat(answer).isEqualTo(expect);
  }

  // cspell:disable
  static Stream<Arguments> test_120956_dataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {"aya", "yee", "u", "maa", "wyeoo"}, //
            1
            //
            ),
        Arguments.of(
            new String[] {"ayaye", "uuuma", "ye", "yemawoo", "ayaa"}, //
            3
            //
            ),
        Arguments.of(
            new String[] {"yayaeye"}, //
            0
            //
            )
        //
        );
  }
  // cspell:enable
}
