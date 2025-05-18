package org.fp024.lv00;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

/*
 * 코딩 테스트 입문 02
 * https://school.programmers.co.kr/learn/challenges/beginner?order=acceptance_desc
 *
 * 먼저 진행하고 있던 테스트 클래스가 너무 커져서, 1000라인 넘어갈 쯤 분리했다. 😅
 */
@Slf4j
class BeginnerExam02Tests {
  /*
   * 캐릭터의 좌표
   * https://school.programmers.co.kr/learn/courses/30/lessons/120861
   */
  @ParameterizedTest
  @MethodSource("test_120861_dataProvider")
  void test_120861(String[] keyinput, int[] board, int[] expect) {

    int[] answer = new int[2];

    int x = 0;
    int y = 0;

    for (int i = 0; i < keyinput.length; i++) {
      if ("left".equals(keyinput[i]) && inArea(x - 1, board[0])) {
        x = x - 1;
      } else if ("right".equals(keyinput[i]) && inArea(x + 1, board[0])) {
        x = x + 1;
      } else if ("up".equals(keyinput[i]) && inArea(y + 1, board[1])) {
        y = y + 1;
      } else if ("down".equals(keyinput[i]) && inArea(y - 1, board[1])) {
        y = y - 1;
      }
    }

    answer[0] = x;
    answer[1] = y;

    assertThat(answer).isEqualTo(expect);
  }

  private boolean inArea(int futurePoint, int side) {
    return Math.abs(futurePoint) <= (side / 2);
  }

  static Stream<Arguments> test_120861_dataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {"left", "right", "up", "right", "right"}, //
            new int[] {11, 11},
            new int[] {2, 1}),
        Arguments.of(
            new String[] {"down", "down", "down", "down", "down"}, //
            new int[] {7, 9},
            new int[] {0, -4})
        //
        );
  }

  /*
   * 외계어 사전
   * https://school.programmers.co.kr/learn/courses/30/lessons/120869
   */
  @ParameterizedTest
  @MethodSource("test_120869_dataProvider")
  void test_120869(String[] spell, String[] dic, int expect) {

    int answer = 2;

    int[] dicCounts = new int[dic.length];

    for (int i = 0; i < dic.length; i++) {
      for (int j = 0; j < spell.length; j++) {
        if (dic[i].contains(spell[j])) {
          dicCounts[i] = dicCounts[i] + 1;
        }
      }
    }

    log.info(Arrays.toString(dicCounts));

    for (int i = 0; i < dicCounts.length; i++) {
      if (dicCounts[i] == spell.length) {
        answer = 1;
      }
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120869_dataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {"p", "o", "s"}, //
            new String[] {"sod", "eocd", "qixm", "adio", "soo"},
            2) //
        ,
        Arguments.of(
            new String[] {"z", "d", "x"}, //
            new String[] {"def", "dww", "dzx", "loveaw"},
            1) //
        ,
        Arguments.of(
            new String[] {"s", "o", "m", "d"}, //
            new String[] {"moos", "dzx", "smm", "sunmmo", "som"},
            2) //
        );
  }

  /*
   * 종이 자르기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120922
   */
  @ParameterizedTest
  @CsvSource({
    "2, 2, 3", //
    "2, 5, 9", //
    "1, 1, 0", //
    "3, 3, 8"
  })
  void test_120922(int M, int N, int expect) {

    int answer = 0;

    int 가로_자른수 = M - 1;
    int 세로_자른_수 = (M) * (N - 1);

    answer = 가로_자른수 + 세로_자른_수;

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 직사각형 넓이 구하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120860
   */
  @ParameterizedTest
  @MethodSource("test_120860_dataProvider")
  void test_120860(int[][] dots, int expect) {

    int answer = 0;

    final int X_IDX = 0;
    final int Y_IDX = 1;

    int xTemp = dots[0][X_IDX];
    int yTemp = dots[0][Y_IDX];
    int xDistance = 0;
    int yDistance = 0;
    for (int i = 1; i < dots.length; i++) {

      if (xTemp == dots[i][X_IDX]) {
        yDistance = calcDistance_120860(yTemp, dots[i][Y_IDX]);
      }

      if (yTemp == dots[i][Y_IDX]) {
        xDistance = calcDistance_120860(xTemp, dots[i][X_IDX]);
      }
    }

    answer = xDistance * yDistance;

    assertThat(answer).isEqualTo(expect);
  }

  private int calcDistance_120860(int a, int b) {
    int max = Math.max(a, b);
    int min = Math.min(a, b);
    return max - min;
  }

  static Stream<Arguments> test_120860_dataProvider() {
    return Stream.of(
        Arguments.of(
            new int[][] {{1, 1}, {2, 1}, {2, 2}, {1, 2}}, //
            1) //
        ,
        Arguments.of(
            new int[][] {{-1, -1}, {1, 1}, {1, -1}, {-1, 1}}, //
            4) //
        );
  }

  /*
   * 로그인 성공
   * https://school.programmers.co.kr/learn/courses/30/lessons/120883
   */
  @ParameterizedTest
  @MethodSource("test_120883_dataProvider")
  void test_120883(String[] id_pw, String[][] db, String expect) {

    String answer = "fail";
    final int ID_IDX = 0;
    final int PW_IDX = 1;

    String id = id_pw[ID_IDX];
    String pw = id_pw[PW_IDX];

    for (String[] idAndPw : db) {

      if (idAndPw[ID_IDX].equals(id)) {
        answer = "wrong pw";
        if (idAndPw[PW_IDX].equals(pw)) {
          answer = "login";
          break;
        }
        break;
      }
    }

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120883_dataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {"meosseugi", "1234"}, //
            new String[][] {
              {"rardss", "123"}, //
              {"yyoom", "1234"},
              {"meosseugi", "1234"},
            },
            "login") //
        ,
        Arguments.of(
            new String[] {"programmer01", "15789"}, //
            new String[][] {
              {"programmer02", "111111"}, //
              {"programmer00", "134"},
              {"programmer01", "1145"},
            },
            "wrong pw") //
        ,
        Arguments.of(
            new String[] {"rabbit04", "98761"}, //
            new String[][] {
              {"jaja11", "98761"}, //
              {"krong0313", "29440"},
              {"rabbit00", "111333"},
            },
            "fail") //
        );
  }

  /*
   * 치킨 쿠폰
   * https://school.programmers.co.kr/learn/courses/30/lessons/120884
   */
  @ParameterizedTest
  @CsvSource({
    "100, 11", //
    "1_081, 120", //
  })
  void test_120884(int chicken, int expect) {

    int answer = -1;

    int orderableChicken = 0;
    int totalOrderableChicken = 0;

    final int couponsToChicken = 10;

    int remainCoupons = chicken;

    while (remainCoupons >= couponsToChicken) {
      orderableChicken = remainCoupons / couponsToChicken;
      remainCoupons = orderableChicken + remainCoupons % couponsToChicken;
      totalOrderableChicken += orderableChicken;
    }

    answer = totalOrderableChicken;
    /*
    10 = 100 / 10

    1  =  10 / 10
    */

    /*
     1081 치킨을 주문했을 때, 쿠폰으로 받을 수 있는 치킨 수

     108 = 1081 / 10
           109 = 108 + 1081 % 10

     10 = 109 / 10
          19 =  10 + 109 % 10

      1 = 19 / 10
          10 = 1 + 19 % 10

      1 = 10 / 10
          1 = 1 + 10 % 10
    */

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 💡 등수 매기기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120882
   */
  @ParameterizedTest
  @MethodSource("test_120882_dataProvider")
  void test_120882(int[][] score, int[] expect) {

    int[] answer;
    double[] avgArray = new double[score.length];
    int[] rankArray = new int[score.length];

    // 💡 굳이 평균을 계산하지 말고 총합으로만 계산하면 double 배열을 만들 필요가 없었다.
    for (int i = 0; i < score.length; i++) {
      avgArray[i] = (score[i][0] + score[i][1]) / 2.0;
    }

    // 💡 내림차순 평균 배열을 만들어야하는 점이 중요했다.
    double[] reversedSortedAvgArray =
        DoubleStream.of(avgArray)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToDouble(Double::doubleValue)
            .toArray();

    log.info("avgArray: " + Arrays.toString(avgArray));
    log.info("sortedArray: " + Arrays.toString(reversedSortedAvgArray));

    for (int i = 0; i < avgArray.length; i++) {
      for (int j = 0; j < reversedSortedAvgArray.length; j++) {
        if (avgArray[i] == reversedSortedAvgArray[j]) {
          rankArray[i] = j + 1;
          break;
        }
      }
    }

    log.info(Arrays.toString(rankArray));

    answer = rankArray;
    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120882_dataProvider() {
    return Stream.of(
        Arguments.of(
            new int[][] {{80, 70}, {90, 50}, {40, 70}, {50, 80}}, //
            new int[] {1, 2, 4, 3} //
            ),
        Arguments.of(
            new int[][] {
              {80, 70}, {70, 80}, {30, 50}, {90, 100}, {100, 90}, {100, 100}, {10, 30}
            }, //
            new int[] {4, 4, 6, 2, 2, 1, 7} //
            ),
        Arguments.of(
            new int[][] {{1, 3}, {3, 1}, {2, 3}, {3, 2}, {1, 2}, {1, 1}}, //
            new int[] {3, 3, 1, 1, 5, 6} //
            )
        //
        );
  }

  /*
   * 저주의 숫자 3
   * https://school.programmers.co.kr/learn/courses/30/lessons/120871
   */
  @ParameterizedTest
  @CsvSource({
    "10, 16", //
    "15, 25", //
    "40, 76"
  })
  void test_120871(int n, int expect) {

    int answer = 0;

    for (int i = 1; i <= n; i++) {

      if (i > 2 && i % 3 == 0) {
        n += 1;
        continue;
      }

      String strNumber = Integer.toString(i);

      if (strNumber.contains("3")) {
        n += 1;
        continue;
      }

      answer = i;
    }

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 💡💡💡 유한소수 판별하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120878
   *
   * 이번껀 진짜... 엉터리로 풀은 것 같은 느낌이 듬..😂😂😂
   */
  @ParameterizedTest
  @CsvSource({
    "7, 20, 1", //
    "11, 22, 1", //
    "12, 21, 2",
    "9, 18, 1",
    "1, 12, 2",
    "2, 1, 1"
  })
  void test_120878(int a, int b, int expect) {

    int answer = 0;

    int d = gcd(a, b);

    int 분모 = b / d;

    // 분모에 대한 소인수 집합 구하기
    SortedSet<Integer> set = new TreeSet<>();
    int number = 분모;

    for (int j = 2; j <= 분모; j++) {
      while (isPrime(j) && number % j == 0) {
        set.add(j);
        number /= j;
      }
    }

    if (분모 == 1) {
      answer = 1;
    } else if (set.size() == 1 && (set.contains(2) || set.contains(5))) {
      answer = 1;
    } else if (set.size() == 2 && (set.contains(2) && set.contains(5))) {
      answer = 1;
    } else {
      answer = 2;
    }

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

  // 소수 판별
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

  /*
   * 문자열 밀기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120921
   */
  @ParameterizedTest
  @CsvSource({
    "hello, ohell, 1",
    "apple, elppa, -1", //
    "atat, tata, 1", //
    "abc, abc, 0", //
    "abcdefga, defgaabc, 5"
  })
  void test_120921(String A, String B, int expect) {

    int answer = -1;

    // 1, 3) 입력된 문자열A의 길이 직전까지 밀면서, 기대 문자열B를 완성할 수 있으면 그 최소 횟수를 반환.
    // 2) 입력된 문자열A의 길이 직전까지 밀면서, 기대 문자열을 완성할 수 없으면, -1.
    // 4) 입력된 문자열과 기대문자열이 같으면 한번도 밀지 않아도 되므로 0.

    final int A_LENGTH = A.length();
    char[] aChars = A.toCharArray();

    if (A.equals(B)) {
      answer = 0;
    } else {
      for (int i = 0; i < A_LENGTH; i++) {
        leftOneShift(aChars);
        if (B.equals(String.valueOf(aChars))) {
          answer = i + 1;
          break;
        }
      }
    }

    assertThat(answer).isEqualTo(expect);
  }

  // 한칸 밀기 메서드
  private void leftOneShift(char[] chars) {
    String tempStr = String.valueOf(chars);
    for (int k = 0; k < chars.length + 1; k++) {
      chars[(k + 1) % chars.length] = tempStr.charAt(k % chars.length);
    }
  }

  /*
   * OX 퀴즈
   * https://school.programmers.co.kr/learn/courses/30/lessons/120907
   */
  @ParameterizedTest
  @MethodSource("test_120907_dataProvider")
  void test_120907(String[] quiz, String[] expect) {

    String[] answer = new String[quiz.length];

    for (int i = 0; i < quiz.length; i++) {
      answer[i] = evalExpression_120907(quiz[i]);
    }

    assertThat(answer).isEqualTo(expect);
  }

  private static final String CORRECT_120907 = "O";
  private static final String INCORRECT_120907 = "X";

  // 수식 평가 메서드
  private String evalExpression_120907(String expression) {
    String[] exps = expression.split(" ");
    int expect = Integer.parseInt(exps[exps.length - 1]);

    String prev = "";
    int result = 0;
    for (int i = 0; i < exps.length - 2; i++) {

      String exp = exps[i]; // -1,  +,  1, -, ...

      if ("+".equals(exp) || "-".equals(exp)) {
        prev = exp;
      } else if ("+".equals(prev)) {
        result += Integer.parseInt(exp);
      } else if ("-".equals(prev)) {
        result -= Integer.parseInt(exp);
      } else {
        result = Integer.parseInt(exp);
      }
    }

    if (result == expect) {
      return CORRECT_120907;
    } else {
      return INCORRECT_120907;
    }
  }

  static Stream<Arguments> test_120907_dataProvider() {
    return Stream.of(
        Arguments.of(
            new String[] {"3 - 4 + 1 + 5 = 5"}, //
            new String[] {"O"}
            //
            ),
        Arguments.of(
            new String[] {"3 - 4 = -3", "5 + 6 = 11"}, //
            new String[] {"X", "O"}
            //
            ),
        Arguments.of(
            new String[] {"19 - 6 = 13", "5 + 66 = 71", "5 - 15 = 63", "3 - 1 = 2"}, //
            new String[] {"O", "O", "X", "O"}
            //
            ) //
        );
  }

  /*
   * 💡💡💡 특이한 정렬
   * https://school.programmers.co.kr/learn/courses/30/lessons/120880
   *
   * 힌트: https://school.programmers.co.kr/questions/84749
   */
  @ParameterizedTest
  @MethodSource("test_120880_dataProvider")
  void test_120880(int[] numlist, int n, int[] expect) {

    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < numlist.length; i++) {
      map.put(numlist[i], Math.abs(numlist[i] - n));
    }

    Comparator<Integer> valueComparator =
        (key1, key2) -> {
          int compare = map.get(key1).compareTo(map.get(key2));
          if (compare == 0) {
            // 거리가 동일할 때... 키 기준으로는 큰수가 앞에 오게한다.
            return key2.compareTo(key1);
          } else {
            return compare;
          }
        };

    SortedMap<Integer, Integer> sortedMap = new TreeMap<>(valueComparator);
    sortedMap.putAll(map);

    log.info(sortedMap.toString());

    int[] answer =
        sortedMap
            .keySet() //
            .stream()
            .mapToInt(Integer::intValue)
            .toArray();

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120880_dataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {1, 2, 3, 4, 5, 6}, //
            4,
            new int[] {4, 5, 3, 6, 2, 1}
            //
            ),
        Arguments.of(
            new int[] {10000, 20, 36, 47, 40, 6, 10, 7000}, //
            30,
            new int[] {36, 40, 20, 47, 10, 6, 7000, 10000}
            //
            )
        //

        );
  }

  /*
   * 다항식 더하기
   * https://school.programmers.co.kr/learn/courses/30/lessons/120863
   */
  @ParameterizedTest
  @CsvSource({
    "3x + 7 + x, 4x + 7", //
    "x + x + x, 3x",
    "x + x, 2x",
    "x, x",
    "123, 123",
    "1 + 2, 3",
    "50x, 50x",
  })
  void test_120863(String polynomial, String expect) {

    String answer = "";

    String[] pArray = polynomial.split("\\s\\+\\s");

    int xSum = 0;
    int num = 0;
    for (String p : pArray) {
      if (p.matches("\\d+x")) {
        xSum += Integer.parseInt(p.replace("x", ""));
      }

      if (p.equals("x")) {
        xSum += 1;
      }

      if (p.matches("\\d+")) {
        num += Integer.parseInt(p);
      }
    }

    log.info(Arrays.toString(pArray));
    log.info("{}", xSum);
    log.info("{}", num);

    if (xSum == 1) {
      answer = "x + " + answer;
    } else if (xSum > 1) {
      answer = xSum + "x + " + answer;
    }

    if (num > 0) {
      answer += Integer.toString(num);
    } else {
      answer = answer.replace(" + ", "");
    }

    log.info("{}", answer);

    assertThat(answer).isEqualTo(expect);
  }

  /*
   * 다음에 올 숫자
   * https://school.programmers.co.kr/learn/courses/30/lessons/120924
   */
  @ParameterizedTest
  @MethodSource("test_120924_dataProvider")
  void test_120863(int[] common, int expect) {

    int answer = 0;

    if (isArithmeticSequence(common)) { // 등차수열일 때.
      log.info("등차");
      answer = common[common.length - 1] + (common[common.length - 1] - common[common.length - 2]);
    } else { // 등비수열일 때.
      log.info("등비");
      answer = common[common.length - 1] * (common[common.length - 1] / common[common.length - 2]);
    }

    log.info("{}", answer);

    assertThat(answer).isEqualTo(expect);
  }

  // 등차수열 인지 확인
  private boolean isArithmeticSequence(int[] common) {
    int diff = common[1] - common[0];
    for (int i = 2; i < common.length; i++) {

      if (diff != common[i] - common[i - 1]) {
        return false;
      }
    }
    return true;
  }

  static Stream<Arguments> test_120924_dataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {1, 2, 3, 4}, //
            5
            //
            ),
        Arguments.of(
            new int[] {2, 4, 8}, //
            16
            //
            )
        //

        );
  }

  /*
   * 💡💡💡 연속된 수의 합
   *     https://school.programmers.co.kr/learn/courses/30/lessons/120923
   *
   * 힌트: https://school.programmers.co.kr/questions/41904
   *       https://school.programmers.co.kr/questions/37874
   *       https://school.programmers.co.kr/questions/46600
   *
   * 😂: 이 문제는 힌트의 공식보고 풀긴했는데... 다시 봐야할 것 같다.
   */
  @ParameterizedTest
  @MethodSource("test_120923_dataProvider")
  void test_120923(int num, int total, int[] expect) {

    int[] answer = new int[num];

    int mid = total / num;
    int first;

    if (num % 2 == 0) {
      first = mid - (num / 2 - 1);
    } else {
      first = mid - (num / 2);
    }
    log.info("first: {}", first);
    for (int i = 0; i < num; i++) {

      log.info("{}", (first + i));
      answer[i] = first + i;
    }
    log.info("------------");

    assertThat(answer).isEqualTo(expect);
  }

  static Stream<Arguments> test_120923_dataProvider() {
    return Stream.of(
        Arguments.of(
            3, //
            12,
            new int[] {3, 4, 5}
            //
            ),
        Arguments.of(
            5, //
            15,
            new int[] {1, 2, 3, 4, 5}
            //
            ),
        Arguments.of(
            4, //
            14,
            new int[] {2, 3, 4, 5}
            //
            ),
        Arguments.of(
            5, //
            5,
            new int[] {-1, 0, 1, 2, 3}
            //
            )
        //
        );
  }
}
