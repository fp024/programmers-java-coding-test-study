package org.fp024.lv00;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 코딩 테스트 입문 00
 * https://school.programmers.co.kr/learn/challenges/beginner?order=acceptance_desc
 *
 * 프로그래머스 웹에서 바로 풀기 힘든 것만 여기다 테스트 코드를 추가해서 실행해보자!
 */
@Slf4j
class BeginnerExam00Tests {

  /*
   * 피자 나눠 먹기 (3)
   * https://school.programmers.co.kr/learn/courses/30/lessons/120816
   */
  @ParameterizedTest
  @CsvSource({
    "7, 10, 2", //
    "4, 12, 3"
  })
  void test_120816(int slice, int n, int expect) {
    log.debug("slice: {}, n: {}", slice, n);

    int answer = n / slice;

    if (n % slice != 0) {
      answer++;
    }

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 제곱수 판별하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120909
   */
  @ParameterizedTest
  @CsvSource({
    "144, 1", //
    "976, 2"
  })
  void test_120909(int n, int expect) {
    log.debug("n: {}", n);
    int sqrt = (int) Math.sqrt(n);
    int answer = sqrt * sqrt == n ? 1 : 2;
    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 최댓값 만들기 (1)
   * https://school.programmers.co.kr/learn/courses/30/lessons/120847
   */
  @ParameterizedTest
  @MethodSource("test_120847_dataProvider")
  void test_120847(int[] numbers, int expect) {

    for (int i = 0; i < 2; i++) {
      for (int j = i; j < numbers.length; j++) {
        if (numbers[i] < numbers[j]) {
          int temp = numbers[i];
          numbers[i] = numbers[j];
          numbers[j] = temp;
        }
      }
    }

    int answer = numbers[0] * numbers[1];

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120847_dataProvider() {
    return Stream.of(
        Arguments.of(new int[] {1, 2, 3, 4, 5}, 20), //
        Arguments.of(new int[] {0, 31, 24, 10, 1, 9}, 744));
  }

  /*
   * 자릿수 더하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120906
   */
  @ParameterizedTest
  @CsvSource({
    "1234, 10", //
    "930211, 16"
  })
  void test_120906(int n, int expect) {

    int answer =
        Integer.toString(n) //
            .chars()
            .reduce(0, (a, b) -> a + (b - '0'));

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 모음 제거
   * https://school.programmers.co.kr/learn/courses/30/lessons/120849
   */
  @ParameterizedTest
  @CsvSource({
    "bus, bs", //
    "nice to meet you, nc t mt y"
  })
  void test_120849(String myString, String expect) {

    String answer = myString.replaceAll("a|e|i|o|u", "");

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 특정 문자 제거하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120826
   */
  @ParameterizedTest
  @CsvSource({
    "abcdef, f, abcde", //
    "BCBdbe, B, Cdbe"
  })
  void test_120826(String my_string, String letter, String expect) {

    // 💡replaceAll()은 정규 표현식을 사용할 때, 사용하자! 단순 치환은 replace()가 낫다.
    String answer = my_string.replace(letter, "");

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 순서쌍의 개수
   * https://school.programmers.co.kr/learn/courses/30/lessons/120836
   */
  @ParameterizedTest
  @CsvSource({
    "20, 6", //
    "100, 9"
  })
  void test_120836(int n, int expect) {
    // 먼저한 내용이 n이 1일 경우에 초기값을 잘못줘서 틀렸었다. 😂
    int answer = n > 1 ? 2 : 1;

    for (int i = 2; i <= n / 2; i++) {

      if (n % i == 0) {
        answer += 1;
      }
    }

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 문자 반복 출력하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120825
   */
  @ParameterizedTest
  @CsvSource({
    "hello, 3, hhheeellllllooo" //
  })
  void test_120825(String my_string, int n, String expect) {

    StringBuilder answer = new StringBuilder("");
    String[] splitStrings = my_string.split("");
    for (String str : splitStrings) {
      for (int i = 0; i < n; i++) {
        answer.append(str);
      }
    }

    assertThat(answer).hasToString(expect);
  }

  /*
   * 배열 자르기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120833
   */
  @ParameterizedTest
  @MethodSource("test_120833_dataProvider")
  void test_120833(int[] numbers, int num1, int num2, int[] expect) {

    int answerSize = num2 - num1 + 1;
    int[] answer = new int[answerSize];

    for (int i = 0, j = num1; j <= num2; i++, j++) {
      answer[i] = numbers[j];
    }

    assertThat(answer).containsExactly(expect);
  }

  static Stream<Arguments> test_120833_dataProvider() {
    return Stream.of(
        Arguments.of(new int[] {1, 2, 3, 4, 5}, 1, 3, new int[] {2, 3, 4}), //
        Arguments.of(new int[] {1, 3, 5}, 1, 2, new int[] {3, 5}));
  }

  /*
   * 피자 나눠 먹기 (1)
   * https://school.programmers.co.kr/learn/courses/30/lessons/120814
   */
  @ParameterizedTest
  @CsvSource({
    "7, 1", //
    "1, 1", //
    "15, 3"
  })
  void test_120814(int n, int expect) {
    final int 기본_조각수 = 7;

    int answer = 0;
    int 몫 = n / 기본_조각수;
    int 나머지 = n % 기본_조각수;

    if (나머지 > 0) {
      answer = 몫 + 1;
    } else {
      answer = 몫;
    }

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 짝수 홀수 개수
   * https://school.programmers.co.kr/learn/courses/30/lessons/120824
   */
  @ParameterizedTest
  @MethodSource("test_120824_dataProvider")
  void test_120824(int[] num_list, int[] expect) {

    int[] answer = new int[] {0, 0};
    final int evenIdx = 0;
    final int oddIdx = 1;

    for (int i = 0; i < num_list.length; i++) {
      if (num_list[i] % 2 == 0) {
        answer[evenIdx] += 1;
      } else {
        answer[oddIdx] += 1;
      }
    }

    assertThat(answer).containsExactly(expect);
  }

  static Stream<Arguments> test_120824_dataProvider() {
    return Stream.of(
        Arguments.of(new int[] {1, 2, 3, 4, 5}, new int[] {2, 3}), //
        Arguments.of(new int[] {1, 3, 5, 7}, new int[] {0, 4}));
  }

  /*
   * 문자열 뒤집기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120822
   */
  @ParameterizedTest
  @CsvSource({
    "jaron, noraj", //
    "bread, daerb" //
  })
  void test_120822(String my_string, String expect) {

    String answer = new StringBuilder(my_string).reverse().toString();

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 점의 위치 구하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120841
   */
  @ParameterizedTest
  @MethodSource("test_120841_dataProvider")
  void test_120841(int[] dot, int expect) {
    int answer = 0;

    int x = dot[0];
    int y = dot[1];

    if (x > 0) {
      if (y > 0) {
        answer = 1;
      } else {
        answer = 4;
      }
    } else {
      if (y > 0) {
        answer = 2;
      } else {
        answer = 3;
      }
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120841_dataProvider() {
    return Stream.of(
        Arguments.of(new int[] {2, 4}, 1), //
        Arguments.of(new int[] {-7, 9}, 2));
  }

  /*
   * 아이스 아메리카노
   * https://school.programmers.co.kr/learn/courses/30/lessons/120819
   */
  @ParameterizedTest
  @MethodSource("test_120819_dataProvider")
  void test_120841(int money, int[] expect) {
    final int 아메리카노_한잔_가격 = 5_500;

    int 몇잔 = money / 아메리카노_한잔_가격;
    int 거스름돈 = money % 아메리카노_한잔_가격;
    int[] answer = {몇잔, 거스름돈};
    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120819_dataProvider() {
    return Stream.of(
        Arguments.of(5_500, new int[] {1, 0}), //
        Arguments.of(15_000, new int[] {2, 4_000}));
  }

  /*
   * 삼각형의 완성조건 (1)
   * https://school.programmers.co.kr/learn/courses/30/lessons/120889
   */
  @ParameterizedTest
  @MethodSource("test_120889_dataProvider")
  void test_120889(int[] sides, int expect) {
    int answer = 0;

    Arrays.sort(sides);
    int a = sides[0];
    int b = sides[1];
    int c = sides[2];

    if (a + b > c) {
      answer = 1;
    } else {
      answer = 2;
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120889_dataProvider() {
    return Stream.of(
        Arguments.of(new int[] {1, 2, 3}, 2), //
        Arguments.of(new int[] {3, 6, 2}, 2),
        Arguments.of(new int[] {199, 72, 222}, 1));
  }

  /*
   * n의 배수 고르기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120905
   */
  @ParameterizedTest
  @MethodSource("test_120905_dataProvider")
  void test_120905(int n, int[] numlist, int[] expect) {

    int[] answer = Arrays.stream(numlist).filter(num -> num % n == 0).toArray();

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120905_dataProvider() {
    return Stream.of(
        Arguments.of(3, new int[] {4, 5, 6, 7, 8, 9, 10, 11, 12}, new int[] {6, 9, 12}), //
        Arguments.of(5, new int[] {1, 9, 3, 10, 13, 5}, new int[] {10, 5}),
        Arguments.of(12, new int[] {2, 100, 120, 600, 12, 12}, new int[] {120, 600, 12, 12}));
  }

  /*
   * 배열의 유사도
   * https://school.programmers.co.kr/learn/courses/30/lessons/120903
   */
  @ParameterizedTest
  @MethodSource("test_120903_dataProvider")
  void test_120903(String[] s1, String[] s2, int expect) {

    int answer = 0;

    String[] 큰_배열;
    String[] 작은_배열;

    if (s1.length > s2.length) {
      큰_배열 = s1;
      작은_배열 = s2;
    } else {
      큰_배열 = s2;
      작은_배열 = s1;
    }

    for (int i = 0; i < 큰_배열.length; i++) {
      for (int j = 0; j < 작은_배열.length; j++) {
        if (큰_배열[i].equals(작은_배열[j])) {
          answer++;
        }
      }
      if (answer == 작은_배열.length) {
        break;
      }
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120903_dataProvider() {
    return Stream.of(
        Arguments.of(new String[] {"a", "b", "c"}, new String[] {"com", "b", "d", "p", "c"}, 2),
        Arguments.of(new String[] {"n", "omg"}, new String[] {"m", "dot"}, 0)
        //
        );
  }

  /*
   * 중복된 숫자 개수
   * https://school.programmers.co.kr/learn/courses/30/lessons/120583
   */
  @ParameterizedTest
  @MethodSource("test_120583_dataProvider")
  void test_120583(int[] array, int n, int expect) {

    int answer = 0;

    for (int i = 0; i < array.length; i++) {
      if (array[i] == n) {
        answer += 1;
      }
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120583_dataProvider() {
    return Stream.of(
        Arguments.of(new int[] {1, 1, 2, 3, 4, 5}, 1, 2), //
        Arguments.of(new int[] {0, 2, 3, 4}, 1, 0)
        //
        );
  }

  /*
   * 배열 두배 만들기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120809
   */
  @ParameterizedTest
  @MethodSource("test_120809_dataProvider")
  void test_120809(int[] numbers, int[] expect) {

    int[] answer = new int[numbers.length];

    for (int i = 0; i < numbers.length; i++) {
      answer[i] = numbers[i] * 2;
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120809_dataProvider() {
    return Stream.of(
        Arguments.of(new int[] {1, 2, 3, 4, 5}, new int[] {2, 4, 6, 8, 10}), //
        Arguments.of(new int[] {1, 2, 100, -99, 1, 2, 3}, new int[] {2, 4, 200, -198, 2, 4, 6})
        //
        );
  }

  /*
   * 중앙값 구하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120811
   */
  @ParameterizedTest
  @MethodSource("test_120811_dataProvider")
  void test_120811(int[] array, int expect) {

    int answer = 0;

    for (int i = 0; i <= array.length / 2; i++) {
      for (int j = i; j < array.length; j++) {

        if (array[i] > array[j]) {
          int temp = array[i];
          array[i] = array[j];
          array[j] = temp;
        }
      }
    }

    answer = array[array.length / 2];

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120811_dataProvider() {
    return Stream.of(
        Arguments.of(new int[] {1}, 1), //
        Arguments.of(new int[] {1, 2, 7, 10, 11}, 7), //
        Arguments.of(new int[] {9, -1, 0}, 0)
        //
        );
  }

  /*
   * 짝수는 싫어요
   * https://school.programmers.co.kr/learn/courses/30/lessons/120813
   */
  @ParameterizedTest
  @MethodSource("test_120813_dataProvider")
  void test_120813(int n, int[] expect) {

    int[] answer = IntStream.rangeClosed(1, n).filter(i -> i % 2 == 1).toArray();

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120813_dataProvider() {
    return Stream.of(
        Arguments.of(10, new int[] {1, 3, 5, 7, 9}),
        Arguments.of(15, new int[] {1, 3, 5, 7, 9, 11, 13, 15}) //
        //
        );
  }

  /*
   * 옷가게 할인 받기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120818
   */
  @ParameterizedTest
  @CsvSource({
    "10, 10",
    "150_000, 142_500", //
    "580_000, 464_000", //
    "1_000_000, 800_000", //
    "100_010, 95_009" // 💡이것 때문에 floor에서 ceil로 바꿈. 올림을 하란 말은 없었던 것 같은데.. 😅
  })
  void test_120822(int price, int expect) {

    int answer = 0;

    if (price >= 500_000) {
      price = price - (int) Math.ceil(price * 0.2);
    } else if (price >= 300_000) {
      price = price - (int) Math.ceil(price * 0.1);
    } else if (price >= 100_000) {
      price = price - (int) Math.ceil(price * 0.05);
    }
    answer = price;

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 직각삼각형 출력하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120823
   */
  @ParameterizedTest
  @MethodSource("test_120823_dataProvider")
  void test_120811(int n, String expect) {

    StringBuilder answer = new StringBuilder();

    for (int i = 0; i < n; i++) {
      for (int j = 0; j <= i; j++) {
        answer.append("*");
      }
      answer.append("%n");
    }

    assertThat(answer).hasToString(expect);
  }

  static Stream<Arguments> test_120823_dataProvider() {
    return Stream.of(
        Arguments.of(1, "*%n"), //
        Arguments.of(2, "*%n**%n"), //
        Arguments.of(3, "*%n**%n***%n")
        //
        );
  }

  /*
   * 개미 군단
   * https://school.programmers.co.kr/learn/courses/30/lessons/120837
   */
  @ParameterizedTest
  @CsvSource({
    "23, 5",
    "24, 6", //
    "999, 201"
  })
  void test_120837(int hp, int expect) {

    final int 장군개미_공격력 = 5;
    final int 병정개미_공격력 = 3;

    int 인원 = 0;

    int 장군_개미수 = hp / 장군개미_공격력;

    if (장군_개미수 > 0) {
      인원 += 장군_개미수;
    }

    int 장군_개미가_공격후_남은_HP = hp % 장군개미_공격력;

    // =====

    int 병정_개미수 = 장군_개미가_공격후_남은_HP / 병정개미_공격력;

    if (병정_개미수 > 0) {
      인원 += 병정_개미수;
    }

    int 병정_개미가_공격후_남은_HP = 장군_개미가_공격후_남은_HP % 병정개미_공격력;

    // ===== 일개미의 공격은 1이므로 그대로 인원에 더해준다.

    인원 += 병정_개미가_공격후_남은_HP;

    int answer = 인원;
    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 가위 바위 보
   * https://school.programmers.co.kr/learn/courses/30/lessons/120839
   */
  @ParameterizedTest
  @CsvSource({
    "2, 0", //
    "205, 052"
  })
  void test_120839(String rsp, String expect) {

    final String 가위 = "2";
    final String 바위 = "0";
    final String 보 = "5";
    final Map<String, String> winMap = Map.of(가위, 바위, 바위, 보, 보, 가위);

    String[] 가위_바위_보 = rsp.split("");

    StringBuilder answer = new StringBuilder();
    for (String val : 가위_바위_보) {
      answer.append(winMap.get(val));
    }

    assertThat(answer).hasToString(expect);
  }

  /*
   * 숨어있는 숫자의 덧셈 (1)
   * https://school.programmers.co.kr/learn/courses/30/lessons/120851
   */
  @ParameterizedTest
  @CsvSource({
    "aAb1B2cC34oOp,	10", //
    "1a2b3c4d123,	16"
  })
  void test_120851(String my_string, int expect) {

    int answer =
        my_string
            .chars() //
            .filter(c -> (c >= '0' && c <= '9'))
            .map(c -> c - '0')
            .sum();

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 최댓값 만들기 (2)
   * https://school.programmers.co.kr/learn/courses/30/lessons/120862
   */
  @ParameterizedTest
  @MethodSource("test_120862_dataProvider")
  void test_120862(int[] numbers, int expect) {
    int answer = Integer.MIN_VALUE;

    for (int i = 0; i < numbers.length; i++) {
      for (int j = 0; j < numbers.length; j++) {
        if (i == j) {
          continue;
        }
        int mul = numbers[i] * numbers[j];
        if (answer <= mul) {
          answer = mul;
        }
      }
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120862_dataProvider() {
    return Stream.of(
        Arguments.of(new int[] {1, 2, -3, 4, -5}, 15), //
        Arguments.of(new int[] {0, -31, 24, 10, 1, 9}, 240), //
        Arguments.of(new int[] {10, 20, 30, 5, 5, 20, 5}, 600)
        //
        );
  }

  /*
   * 대문자와 소문자
   * https://school.programmers.co.kr/learn/courses/30/lessons/120893
   */
  @ParameterizedTest
  @CsvSource({
    "cccCCC, CCCccc", //
    "abCdEfghIJ, ABcDeFGHij"
  })
  void test_120893(String my_string, String expect) {

    StringBuilder sb = new StringBuilder(my_string);

    for (int i = 0; i < sb.length(); i++) {
      char c = sb.charAt(i);
      if (Character.isUpperCase(c)) {
        sb.setCharAt(i, Character.toLowerCase(c));
      } else {
        sb.setCharAt(i, Character.toUpperCase(c));
      }
    }

    String answer = sb.toString();

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 인덱스 바꾸기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120895
   */
  @ParameterizedTest
  @CsvSource({
    "hello,	1,	2,	hlelo", //
    "I love you, 3, 6, I l veoyou"
  })
  void test_120895(String my_string, int num1, int num2, String expect) {

    StringBuilder sb = new StringBuilder(my_string);
    char temp = sb.charAt(num1);
    sb.setCharAt(num1, sb.charAt(num2));
    sb.setCharAt(num2, temp);

    String answer = sb.toString();

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 약수 구하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120897
   */
  @ParameterizedTest
  @MethodSource("test_120897_dataProvider")
  void test_120897(int n, int[] expect) {
    int[] answer;
    if (n == 1) {
      answer = new int[] {1};
    } else {
      int[] array = IntStream.rangeClosed(1, n / 2 + 1).filter(i -> n % i == 0).toArray();
      answer = new int[array.length + 1];

      for (int i = 0; i < array.length; i++) {
        answer[i] = array[i];
      }
      answer[answer.length - 1] = n;
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120897_dataProvider() {
    return Stream.of(
        Arguments.of(24, new int[] {1, 2, 3, 4, 6, 8, 12, 24}), //
        Arguments.of(29, new int[] {1, 29}) //
        //
        );
  }

  /*
   * 가장 큰 수 찾기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120899
   *
   * 💡첫번째 반복에서 위치까지 찾을 수 있는데, 괜히 검색을 또했었다. 코드는 고쳐놨음.
   */
  @ParameterizedTest
  @MethodSource("test_120899_dataProvider")
  void test_120899(int[] array, int[] expect) {
    int[] answer = new int[2];

    answer[0] = array[0];

    for (int i = 1; i < array.length; i++) {
      if (answer[0] < array[i]) {
        answer[0] = array[i];
        answer[1] = i;
      }
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120899_dataProvider() {
    return Stream.of(
        Arguments.of(new int[] {1, 8, 3}, new int[] {8, 1}), //
        Arguments.of(new int[] {9, 10, 11, 8}, new int[] {11, 2}) //
        //
        );
  }

  /*
   * 문자열 정렬하기 (1)
   * https://school.programmers.co.kr/learn/courses/30/lessons/120850
   */
  @ParameterizedTest
  @MethodSource("test_120850_dataProvider")
  void test_120850(String my_string, int[] expect) {

    int[] answer =
        my_string
            .chars() //
            .filter(c -> (c >= '0' && c <= '9'))
            .map(c -> c - '0')
            .sorted()
            .toArray();

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120850_dataProvider() {
    return Stream.of(
        Arguments.of("hi12392", new int[] {1, 2, 2, 3, 9}), //
        Arguments.of("p2o4i8gj2", new int[] {2, 2, 4, 8}), //
        Arguments.of("abcde0", new int[] {0})
        //
        );
  }

  /*
   * 암호 해독
   * https://school.programmers.co.kr/learn/courses/30/lessons/120892
   */
  @ParameterizedTest
  @MethodSource("test_120892_dataProvider")
  void test_120892(String cipher, int code, String expect) {

    StringBuilder answer = new StringBuilder();

    char[] cipherArray = cipher.toCharArray();

    for (int i = code - 1; i < cipherArray.length; i += code) {
      answer.append(cipherArray[i]);
    }

    assertThat(answer).hasToString(expect);
  }

  static Stream<Arguments> test_120892_dataProvider() {
    return Stream.of(
        Arguments.of("a", 1, "a"), //
        Arguments.of("dfjardstddetckdaccccdegk", 4, "attack"), //
        Arguments.of("pfqallllabwaoclk", 2, "fallback")
        //
        );
  }

  /*
   * 주사위의 개수
   * https://school.programmers.co.kr/learn/courses/30/lessons/120845
   */
  @ParameterizedTest
  @MethodSource("test_120845_dataProvider")
  void test_120845(int[] box, int n, int expect) {

    int answer = 1;

    for (int b : box) {
      answer *= (b / n);
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120845_dataProvider() {
    return Stream.of(
        Arguments.of(new int[] {1, 1, 1}, 1, 1), //
        Arguments.of(new int[] {10, 8, 6}, 3, 12)
        //
        );
  }

  /*
   * 문자열 정렬하기 (2)
   * https://school.programmers.co.kr/learn/courses/30/lessons/120911
   */
  @ParameterizedTest
  @CsvSource({
    "Bcad, abcd", //
    "heLLo, ehllo", //
    "Python,hnopty"
  })
  void test_120911(String my_string, String expect) {

    String answer =
        my_string
            .toLowerCase()
            .chars()
            .sorted()
            .mapToObj(cp -> String.valueOf((char) cp))
            .collect(Collectors.joining());

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 피자 나눠 먹기 (2)
   * https://school.programmers.co.kr/learn/courses/30/lessons/120815
   */
  @ParameterizedTest
  @CsvSource({
    "6, 1", //
    "10, 5", //
    "4, 2"
  })
  void test_120815(int n, int expect) {

    final int 기본_피자_조각수 = 6;

    int answer = 0;

    int x = 1;
    for (; (x * 기본_피자_조각수) % n != 0; x++)
      ;

    answer = x;

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 외계행성의 나이
   * https://school.programmers.co.kr/learn/courses/30/lessons/120834
   */
  @ParameterizedTest
  @CsvSource({
    "23, cd", //
    "51, fb", //
    "100, baa"
  })
  void test_120834(int age, String expect) {

    String answer = "";

    int diff = 'a' - '0';

    StringBuilder sb = new StringBuilder(Integer.toString(age));
    for (int i = 0; i < sb.length(); i++) {
      sb.setCharAt(i, (char) (sb.charAt(i) + diff));
    }

    answer = sb.toString();

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 배열 회전시키기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120844
   */
  @ParameterizedTest
  @MethodSource("test_120844_dataProvider")
  void test_120844(int[] numbers, String direction, int[] expect) {

    int[] answer = new int[numbers.length];

    if (direction.equals("right")) {
      for (int i = 0; i < numbers.length - 1; i++) {
        answer[i + 1] = numbers[i];
      }
      answer[0] = numbers[numbers.length - 1];
    } else {
      for (int i = 1; i < numbers.length; i++) {
        answer[i - 1] = numbers[i];
      }
      answer[numbers.length - 1] = numbers[0];
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120844_dataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {1, 2, 3}, //
            "right",
            new int[] {3, 1, 2}), //
        Arguments.of(
            new int[] {4, 455, 6, 4, -1, 45, 6}, //
            "left",
            new int[] {455, 6, 4, -1, 45, 6, 4})
        //
        );
  }

  /*
   * 369게임
   * https://school.programmers.co.kr/learn/courses/30/lessons/120891
   */
  @ParameterizedTest
  @CsvSource({
    "3, 1", //
    "29423, 2"
  })
  void test_120891(int order, int expect) {

    int answer = 0;

    answer =
        (int)
            Integer.toString(order) //
                .chars()
                .filter(c -> "369".indexOf(c) >= 0)
                .count();

    assertThat(answer).isEqualTo(expect);
  }
}
