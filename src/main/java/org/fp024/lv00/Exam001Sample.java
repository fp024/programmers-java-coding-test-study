package org.fp024.lv00;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.fp024.common.Runner;

public class Exam001Sample implements Runner<int[]> {

  @Override
  public int[] run(String[] args) throws Exception {

    List<Integer> inputList = new ArrayList<>();

    try (Scanner sc = new Scanner(System.in)) {
      while (sc.hasNext()) {
        inputList.add(sc.nextInt());
      }
    }
    return inputList.stream() //
        .mapToInt(Integer::intValue)
        .toArray();
  }
}
