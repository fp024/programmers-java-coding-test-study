package org.fp024.lv00;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 코딩 테스트 입문 01
 * https://school.programmers.co.kr/learn/challenges/beginner?order=acceptance_desc
 *
 * 먼저 진행하고 있던 테스트 클래스가 너무 커져서, 1000라인 넘어갈 쯤 분리했다. 😅
 */
@Slf4j
class BeginnerExam01Tests {
  /*
   * 숫자 찾기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120904
   */
  @ParameterizedTest
  @CsvSource({
    "29183, 1, 3", //
    "232443, 4, 4",
    "123456, 7, -1"
  })
  void test_120904(int num, int k, int expect) {

    int answer = 0;

    int index = Integer.toString(num).indexOf(Integer.toString(k));
    answer = index >= 0 ? index + 1 : index;

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 💡 합성수 찾기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120846
   */
  @ParameterizedTest
  @CsvSource({
    "10, 5", //
    "15, 8"
  })
  void test_120846(int n, int expect) {

    int answer = 0;
    int count = 0;
    for (int i = 1; i <= n; i++) {
      if (getDivisorCount(i) > 2) {
        count += 1;
      }
    }
    answer = count;

    assertThat(answer).isEqualTo(expect);
  }

  private int getDivisorCount(int x) {
    if (x == 1) {
      return 1;
    }

    int c = 2;
    for (int i = 2; i <= x / 2; i++) {
      if (x % i == 0) {
        c++;
      }
    }
    return c;
  }

  /*
   * 중복된 문자 제거
   * https://school.programmers.co.kr/learn/courses/30/lessons/120888
   */
  @ParameterizedTest
  @CsvSource({
    "people, peol", //
    "We are the world, We arthwold"
  })
  void test_120888(String my_string, String expect) {

    String answer = "";

    char[] chars = my_string.toCharArray();

    Set<Character> cset = new HashSet<>();
    StringBuilder sb = new StringBuilder();

    for (char c : chars) {
      if (!cset.contains(c)) {
        cset.add(c);
        sb.append(c);
      }
    }

    answer = sb.toString();

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 분수의 덧셈
   * https://school.programmers.co.kr/learn/courses/30/lessons/120808
   */
  @ParameterizedTest
  @MethodSource("test_120808_dataProvider")
  void test_120808(int numer1, int denom1, int numer2, int denom2, int[] expect) {

    int[] answer = new int[2];

    int 분모 = denom1 * denom2;
    int 분자 = numer1 * denom2 + numer2 * denom1;

    int d = gcd(분모, 분자);

    answer[0] = 분자 / d;
    answer[1] = 분모 / d;

    assertThat(answer).isEqualTo(expect);
  }

  // 💡 유클리드 호제법을 이용하여 GCD를 계산하는 메서드
  //  GCD 함수 만드는 법은 좀 더 이해가 필요할 것 같다.
  private int gcd(int a, int b) {
    while (b != 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }

  static Stream<Arguments> test_120808_dataProvider() {
    return Stream.of(
        Arguments.of(1, 2, 3, 4, new int[] {5, 4}), //
        Arguments.of(9, 2, 1, 3, new int[] {29, 6}));
  }

  /*
   * 최빈값 구하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120812
   */
  @ParameterizedTest
  @MethodSource("test_120812_dataProvider")
  void test_120812(int[] array, int expect) {

    int answer = 0;
    Map<Integer, Integer> map = new HashMap<>();

    for (int a : array) {
      Integer count = map.get(a);
      map.put(a, count == null ? 1 : count + 1);
    }

    int maxCount = 0;
    int maxValue = 0;
    for (Entry<Integer, Integer> m : map.entrySet()) {
      if (maxCount < m.getValue()) {
        maxCount = m.getValue();
        maxValue = m.getKey();
      }
    }

    // 2개 이상 중복 구하기
    int maxDupCount = 0;
    for (Entry<Integer, Integer> m : map.entrySet()) {
      if (m.getValue() == maxCount) {
        maxDupCount += 1;
        if (maxDupCount > 1) {
          break;
        }
      }
    }

    answer = maxDupCount > 1 ? -1 : maxValue;

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120812_dataProvider() {
    return Stream.of(
        Arguments.of(new int[] {1, 2, 3, 3, 3, 4}, 3),
        Arguments.of(new int[] {1, 1, 2, 2}, -1),
        Arguments.of(new int[] {1}, 1)
        //
        );
  }

  /*
   * 모스부호 (1)
   * https://school.programmers.co.kr/learn/courses/30/lessons/120838
   */
  @ParameterizedTest
  @CsvSource({
    ".... . .-.. .-.. ---, hello", //
    ".--. -.-- - .... --- -.,	python"
  })
  void test_120838(String letter, String expect) {

    String answer = "";

    Map<String, String> map = new HashMap<>();
    map.put(".-", "a");
    map.put("-...", "b");
    map.put("-.-.", "c");
    map.put("-..", "d");
    map.put(".", "e");
    map.put("..-.", "f");
    map.put("--.", "g");
    map.put("....", "h");
    map.put("..", "i");
    map.put(".---", "j");
    map.put("-.-", "k");
    map.put(".-..", "l");
    map.put("--", "m");
    map.put("-.", "n");
    map.put("---", "o");
    map.put(".--.", "p");
    map.put("--.-", "q");
    map.put(".-.", "r");
    map.put("...", "s");
    map.put("-", "t");
    map.put("..-", "u");
    map.put("...-", "v");
    map.put(".--", "w");
    map.put("-..-", "x");
    map.put("-.--", "y");
    map.put("--..", "z");

    String[] letters = letter.split(" ");

    StringBuilder sb = new StringBuilder();
    for (String l : letters) {
      sb.append(map.get(l));
    }

    answer = sb.toString();

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 2차원으로 만들기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120812
   */
  @ParameterizedTest
  @MethodSource("test_120842_dataProvider")
  void test_120842(int[] num_list, int n, int[][] expect) {

    final int REQ_IDX = num_list.length;
    final int FST_IDX = REQ_IDX / n;
    final int SND_IDX = n;

    int[][] answer = new int[FST_IDX][SND_IDX];

    for (int i = 0, t = 0; i < FST_IDX; i++) {
      for (int j = 0; j < SND_IDX; j++) {
        answer[i][j] = num_list[t++];
      }
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120842_dataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {1, 2, 3, 4, 5, 6, 7, 8}, //
            2,
            new int[][] {{1, 2}, {3, 4}, {5, 6}, {7, 8}}),
        Arguments.of(
            new int[] {100, 95, 2, 4, 5, 6, 18, 33, 948}, //
            3,
            new int[][] {{100, 95, 2}, {4, 5, 6}, {18, 33, 948}}));
  }

  /*
   * k의 개수
   * https://school.programmers.co.kr/learn/courses/30/lessons/120887
   */
  @ParameterizedTest
  @CsvSource({
    "1, 13, 1, 6", //
    "10, 50, 5, 5",
    "3, 10, 2, 0"
  })
  void test_120887(int i, int j, int k, int expect) {

    int answer = 0;

    int sum = 0;
    for (int a = i; a <= j; a++) {
      String strA = Integer.toString(a);

      for (String c : strA.split("")) {
        if (c.equals(Integer.toString(k))) {
          sum += 1;
        }
      }
    }

    answer = sum;

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * A로 B 만들기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120886
   */
  @ParameterizedTest
  @CsvSource({
    "olleh, hello, 1", //
    "allpe, apple, 0"
  })
  void test_120886(String before, String after, int expect) {

    int answer = 0;

    String[] beforeArray = before.split("");
    String[] afterArray = after.split("");

    Arrays.sort(beforeArray);
    Arrays.sort(afterArray);

    log.info(Arrays.toString(beforeArray));
    log.info(Arrays.toString(afterArray));

    answer = 1;
    for (int i = 0; i < beforeArray.length; i++) {
      if (!beforeArray[i].equals(afterArray[i])) {
        answer = 0;
      }
    }

    // 반복으로 확인할 필요가 없었다,
    // 정렬하면 같은지 여부만 알면 되기 때문에 다음과 같은 식으로 처리가 가능하다.😅
    // String(a).equals(new String(b)) ? 1 :0;

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 진료 순서 정하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120835
   *
   * 💡난 정렬을 하긴 했는데.. 자신보다 큰값을 카운트하면 그것이 순위? 다음에 이점을 고려해보자!
   */
  @ParameterizedTest
  @MethodSource("test_120835_dataProvider")
  void test_120835(int[] emergency, int[] expect) {
    final int N = emergency.length;

    int[] answer = new int[N];
    int[] sortedArray = new int[N];

    for (int i = 0; i < N; i++) {
      sortedArray[i] = emergency[i];
    }
    Arrays.sort(sortedArray);

    for (int i = 0; i < N; i++) {
      int checkVal = emergency[i];

      for (int j = 0; j < N; j++) {
        if (checkVal == sortedArray[j]) {
          answer[i] = N - j;
        }
      }
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120835_dataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {3, 76, 24}, //
            new int[] {3, 1, 2}),
        Arguments.of(
            new int[] {1, 2, 3, 4, 5, 6, 7}, //
            new int[] {7, 6, 5, 4, 3, 2, 1}),
        Arguments.of(
            new int[] {30, 10, 23, 6, 100}, //
            new int[] {2, 4, 3, 5, 1})
        //

        );
  }

  /*
   * 팩토리얼
   * https://school.programmers.co.kr/learn/courses/30/lessons/120848
   */
  @ParameterizedTest
  @CsvSource({
    "3628800, 10", //
    "7, 3"
  })
  void test_120848(int n, int expect) {

    int answer = 0;

    int f = 1;
    int i = 1;

    while ((f = f * i) <= n) {
      i += 1;
    }

    answer = i - 1;

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 숨어있는 숫자의 덧셈 (2)
   * https://school.programmers.co.kr/learn/courses/30/lessons/120864
   * 💡 s == "" 이렇게 비교하면 좀 문제가 생길때가 있는 것 같다. String은 equals로 비교하자!
   */
  @ParameterizedTest
  @CsvSource({
    "aAb1B2cC34oOp, 37", //
    "1a2b3c4d123Z, 133",
    "123, 123",
    "aaaaaa, 0",
    "R9, 9",
  })
  void test_120864(String my_string, int expect) {

    int answer = 0;

    String[] str =
        my_string //
            .replaceAll("[a-zA-Z]+", " ")
            .trim()
            .split("\\s+");

    log.info(Arrays.toString(str));

    for (String s : str) {
      answer += ("".equals(s)) ? 0 : Integer.parseInt(s);
    }

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 💡 구슬을 나누는 경우의 수
   * https://school.programmers.co.kr/learn/courses/30/lessons/120840
   *
   * ✨ factorial 만들어서 하다가 안되서, 다음 답변글 보고 제출했다. 😅
   * 출처: https://school.programmers.co.kr/questions/80508
   */
  @ParameterizedTest
  @CsvSource({
    "3, 2, 3", //
    "5, 3, 10",
    "30, 29, 30"
  })
  void test_120840(int balls, int share, long expect) {

    long answer = 1;

    for (int i = 1; i <= share; i++) {
      answer *= (balls - i + 1);
      answer /= i;
    }

    assertThat(answer).isEqualTo(expect);
  }

  /*
   숫자가 너무 커져서, 실시간으로 곱하고 나누는게 적절하다.

   (1 x 2 ... 29 x 30)
   ---------------------
   () * (1 x 2 ... 29)


   (1 x 2 ... 29 x 30)
   ---------------------
   (1) * (1 x 2 ... 29)


   (1 x 2 ... 29 x 30)
   ---------------------
   (1 x 2) * (1 x 2 ... 28)


   (1 x 2 ... 29 x 30)
   ---------------------
   (1 x ... x 15) * (1 x 2 ... 15)
  */

  // ===========================================

  /*
   * 💡 가까운 수
   * https://school.programmers.co.kr/learn/courses/30/lessons/120890
   */
  @ParameterizedTest
  @MethodSource("test_120890_dataProvider")
  void test_120890(int[] array, int n, int expect) {
    int answer = 0;

    Arrays.sort(array);

    int[] diffs = new int[array.length];

    for (int i = 0; i < array.length; i++) {
      diffs[i] = array[i] - n;
    }

    log.info("n: {}", n);
    log.info(Arrays.toString(array));
    log.info(Arrays.toString(diffs));

    int minDiff = diffs[0];
    int minDiffIdx = 0;
    for (int i = 1; i < diffs.length; i++) {

      if (Math.abs(diffs[i]) == Math.abs(minDiff)) {
        continue;
      }
      if (Math.abs(diffs[i]) <= Math.abs(minDiff)) {
        minDiff = diffs[i];
        minDiffIdx = i;
      }
    }

    answer = array[minDiffIdx];

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120890_dataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {10, 11, 12}, //
            13,
            12),
        Arguments.of(
            new int[] {20, 10}, //
            15,
            10),
        Arguments.of(
            new int[] {3, 10, 28}, //
            20,
            28),
        Arguments.of(
            new int[] {10, 11, 13, 14, 15}, //
            13,
            13),
        Arguments.of(
            new int[] {10, 12}, //
            11,
            10),
        Arguments.of(
            new int[] {3, 3, 4, 4, 5, 5, 6, 6}, //
            5,
            5)
        //
        );
  }

  /*
   * 한 번만 등장한 문자
   * https://school.programmers.co.kr/learn/courses/30/lessons/120896
   */
  @ParameterizedTest
  @CsvSource({
    "abcabcadc, d", //
    "abdc, abcd",
    "hello, eho"
  })
  void test_120896(String s, String expect) {

    String answer = "";

    SortedMap<String, Integer> countMap = new TreeMap<>();

    for (String str : s.split("")) {
      countMap.put(str, countMap.getOrDefault(str, 0) + 1);
    }

    log.info("{}", countMap);

    answer =
        countMap.entrySet().stream()
            .filter(e -> e.getValue() == 1)
            .map(Entry::getKey)
            .collect(Collectors.joining());

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 7의 개수
   * https://school.programmers.co.kr/learn/courses/30/lessons/120912
   */
  @ParameterizedTest
  @MethodSource("test_120912_dataProvider")
  void test_120912(int[] array, int expect) {

    int answer = 0;

    for (int a : array) {

      for (String s : String.valueOf(a).split("")) {
        if ("7".equals(s)) {
          answer += 1;
        }
      }
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120912_dataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {7, 77, 17}, //
            4),
        Arguments.of(
            new int[] {10, 29}, //
            0)
        //
        );
  }

  /*
   * 컨트롤 제트
   * https://school.programmers.co.kr/learn/courses/30/lessons/120853
   */
  @ParameterizedTest
  @CsvSource({
    "1 2 Z 3,	4", //
    "10 20 30 40, 100",
    "10 Z 20 Z 1, 1",
    "10 Z 20 Z,	0",
    "-1 -2 -3 Z, -3"
  })
  void test_120853(String s, long expect) {

    int answer = 0;

    int prev = 0;
    int sum = 0;

    for (String str : s.split(" ")) {
      if ("Z".equals(str)) {
        sum -= prev;
      } else {
        prev = Integer.valueOf(str);
        sum += prev;
      }
    }
    answer = sum;

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 💡 소인수 분해
   * https://school.programmers.co.kr/learn/courses/30/lessons/120852
   *
   * ✨ 힌트: https://school.programmers.co.kr/questions/44899
   */
  @ParameterizedTest
  @MethodSource("test_120852_dataProvider")
  void test_120852(int n, int[] expect) {

    SortedSet<Integer> set = new TreeSet<>();

    for (int i = n; i >= n; i--) {

      for (int j = 2; j <= n; j++) {
        if (!isPrime(j)) {
          continue;
        }

        if (i % j == 0) {
          log.info("{}, i:{}, j:{}", i % j, i, j);
          i = i / j;
          set.add(j);
        }
      }
    }

    int[] answer = new int[set.size()];

    for (int i = 0; i < answer.length; i++) {
      answer[i] = set.first();
      set.remove(answer[i]);
    }

    assertThat(answer).isEqualTo(expect);
  }

  // 소수판별
  private boolean isPrime(int n) {
    if (n <= 1) {
      return false;
    }
    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }

  static Stream<Arguments> test_120852_dataProvider() {
    return Stream.of(
        Arguments.of(
            12, //
            new int[] {2, 3}),
        Arguments.of(
            17, //
            new int[] {17}),
        Arguments.of(
            420, //
            new int[] {2, 3, 5, 7}),
        Arguments.of(
            256, //
            new int[] {2}));
  }

  /* 💡 소인수 분해 - Copilot이 앞의 코드 정리
   * https://school.programmers.co.kr/learn/courses/30/lessons/120852
   */
  @ParameterizedTest
  @MethodSource("test_enhance_120852_dataProvider")
  void test_enhance_120852(int n, int[] expect) {
    SortedSet<Integer> set = new TreeSet<>();
    int number = n;

    for (int j = 2; j <= n; j++) {
      while (isPrime(j) && number % j == 0) {
        set.add(j);
        number /= j;
      }
    }

    int[] answer = new int[set.size()];
    int index = 0;
    for (Integer prime : set) {
      answer[index++] = prime;
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_enhance_120852_dataProvider() {
    return Stream.of(
        Arguments.of(12, new int[] {2, 3}),
        Arguments.of(17, new int[] {17}),
        Arguments.of(420, new int[] {2, 3, 5, 7}),
        Arguments.of(256, new int[] {2}));
  }

  /*
   * 이진수 더하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120885
   */
  @ParameterizedTest
  @CsvSource({
    "10, 11, 101", //
    "1001, 1111, 11000"
  })
  void test_120885(String bin1, String bin2, String expect) {

    String answer = "";

    String longer;
    String shorter;

    if (bin1.length() > bin2.length()) {
      longer = bin1;
      shorter = bin2;
    } else {
      longer = bin2;
      shorter = bin1;
    }

    StringBuilder zeroPrefixPadding = new StringBuilder(shorter);

    for (int i = 0; i < longer.length() - shorter.length(); i++) {
      zeroPrefixPadding.insert(0, "0");
    }

    log.info(zeroPrefixPadding.toString());

    int carry = 0;
    StringBuilder sb = new StringBuilder();
    for (int i = zeroPrefixPadding.length() - 1; i >= -1; i--) {

      if (i == -1) {
        if (carry > 0) {
          sb.insert(0, carry);
        }
        break;
      }

      int a = zeroPrefixPadding.charAt(i) - '0';
      int b = longer.charAt(i) - '0';

      log.info("a: {}, b: {}, 올림수: {}", a, b, carry);

      if (a + b + carry > 1) {
        sb.insert(0, ((a + b + carry) % 2));
        carry = 1;
      } else {
        sb.insert(0, (a + b + carry));
        carry = 0;
      }
    }

    answer = sb.toString();

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 이진수 더하기 - Copilot이 나의 코드 개선
   * 💡나중에 확인해보자! 바로 머리에 들어오진 않는다. 😂
   */
  @ParameterizedTest
  @CsvSource({
    "10, 11, 101", //
    "1001, 1111, 11000"
  })
  void test_copilot_120885(String bin1, String bin2, String expect) {

    String answer = copilot_120885_addBinary(bin1, bin2);

    assertThat(answer).isEqualTo(expect);
  }

  private String copilot_120885_addBinary(String bin1, String bin2) {
    StringBuilder sb = new StringBuilder();
    int i = bin1.length() - 1, j = bin2.length() - 1, carry = 0;

    while (i >= 0 || j >= 0 || carry > 0) {
      int a = (i >= 0) ? bin1.charAt(i--) - '0' : 0;
      int b = (j >= 0) ? bin2.charAt(j--) - '0' : 0;

      int sum = a + b + carry;
      sb.insert(0, sum % 2);
      carry = sum / 2;
    }

    return sb.toString();
  }

  /* 공 던지기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120843
   */
  @ParameterizedTest
  @MethodSource("test_120843_dataProvider")
  void test_120843(int[] numbers, int k, int expect) {
    int answer = 0;

    int n = numbers.length;

    for (int i = 0, j = 1; j <= k; i += 2, j++) {
      log.info("i: {}, numbers[i % n]: {}", i, numbers[i % n]);
      answer = numbers[i % n];
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120843_dataProvider() {
    return Stream.of(
        Arguments.of(new int[] {1, 2, 3, 4}, 2, 3), //
        Arguments.of(new int[] {1, 2, 3, 4, 5, 6}, 5, 3), //
        Arguments.of(new int[] {1, 2, 3}, 3, 2), //
        Arguments.of(new int[] {1, 2, 3}, 5, 3)
        //
        );
  }

  /*
   * 잘라서 배열로 저장하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120913
   */
  @ParameterizedTest
  @MethodSource("test_120913_dataProvider")
  void test_120913(String my_str, int n, String[] expect) {

    int myStrLength = my_str.length();

    int answerLength = myStrLength % n > 0 ? myStrLength / n + 1 : myStrLength / n;

    String[] answer = new String[answerLength];

    for (int i = 0; i < answer.length; i++) {
      answer[i] = "";
    }

    for (int i = 0, d = 0; i < myStrLength; i++) {
      d = i / n;
      answer[d] = answer[d].concat(String.valueOf(my_str.charAt(i)));
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120913_dataProvider() {
    return Stream.of(
        Arguments.of("abc1Addfggg4556b", 6, new String[] {"abc1Ad", "dfggg4", "556b"}), //
        Arguments.of("abcdef123", 3, new String[] {"abc", "def", "123"}) //
        //
        );
  }

  /*
   * 문자열 계산하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120902
   */
  @ParameterizedTest
  @CsvSource({
    "3 + 4,	7", //
    "1 + 2 + 3 + 4,	10",
    "1 + 2 + 3 - 4,	2",
  })
  void test_120902(String my_string, int expect) {

    int answer = 0;

    String[] myStrArray = my_string.split(" ");

    String prev = "";
    for (String s : myStrArray) {
      if ("+".equals(s) || "-".equals(s)) {
        prev = s;
      } else if ("+".equals(prev)) {
        answer = answer + Integer.parseInt(s);
      } else if ("-".equals(prev)) {
        answer = answer - Integer.parseInt(s);
      } else {
        answer = Integer.parseInt(s);
      }
    }

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 💡 삼각형의 완성조건 (2)
   * https://school.programmers.co.kr/learn/courses/30/lessons/120868
   *
   * 💡 힌트: https://school.programmers.co.kr/questions/39391
   *           댓글에서 도움을 받은 느낌? 😅
   * 💡 힌트: https://school.programmers.co.kr/questions/41515
   *           입출력 예를 보면.. 공식이 생김.. 😅
   *  뭔가 이상하게 풀은 느낌이 강한데... 일단 두고 개선하자!
   */
  @ParameterizedTest
  @MethodSource("test_120868_dataProvider")
  void test_120868(int[] sides, int expect) {

    int answer = 0;

    // sides를 정렬해 둠.
    if (sides[0] > sides[1]) {
      int temp = sides[0];
      sides[0] = sides[1];
      sides[1] = temp;
    }

    int aCase = (int) IntStream.rangeClosed(sides[1] - sides[0] + 1, sides[1]).count();
    int bCase = (int) IntStream.range(sides[1] + 1, sides[0] + sides[1]).count();

    answer = aCase + bCase;

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120868_dataProvider() {
    return Stream.of(
        Arguments.of(new int[] {1, 2}, 1), //
        Arguments.of(new int[] {3, 6}, 5), //
        Arguments.of(new int[] {11, 7}, 13) //
        //
        );
  }

  /*
   * 영어가 싫어요
   * https://school.programmers.co.kr/learn/courses/30/lessons/120894
   */
  @ParameterizedTest
  @CsvSource({
    "onetwothreefourfivesixseveneightnine, 123456789", //
    "onefourzerosixseven, 14067"
  })
  void test_120894(String numbers, long expect) {

    long answer = 0;

    // 💡 배열의 인덱스를 활용했으면 굳이 Map을 안써도 되었을 텐데...
    Map<String, String> map = new HashMap<>();
    map.put("one", "1");
    map.put("two", "2");
    map.put("three", "3");
    map.put("four", "4");
    map.put("five", "5");
    map.put("six", "6");
    map.put("seven", "7");
    map.put("eight", "8");
    map.put("nine", "9");
    map.put("zero", "0");

    for (Entry<String, String> entry : map.entrySet()) {
      numbers = numbers.replace(entry.getKey(), entry.getValue());
    }

    answer = Long.valueOf(numbers);

    assertThat(answer).isEqualTo(expect);
  }
}
