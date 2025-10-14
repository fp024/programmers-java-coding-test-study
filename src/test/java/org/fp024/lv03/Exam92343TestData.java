package org.fp024.lv03;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

class Exam92343TestData {
  // cspell:disable
  static Stream<Arguments> defaultDataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, //
            new int[][] {
              {0, 1}, //
              {1, 2}, //
              {1, 4}, //
              {0, 8}, //
              {8, 7}, //
              {9, 10}, //
              {9, 11}, //
              {4, 3}, //
              {6, 5}, //
              {4, 6}, //
              {8, 9}
            },
            5
            //
            ),
        Arguments.of(
            new int[] {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0}, //
            new int[][] {
              {0, 1}, //
              {0, 2}, //
              {1, 3}, //
              {1, 4}, //
              {2, 5}, //
              {2, 6}, //
              {3, 7}, //
              {4, 8}, //
              {6, 9}, //
              {9, 10}
            },
            5
            //
            )
        //
        );
  }

  static Stream<Arguments> buildTreeTestDataProvider() {
    return Stream.of(
        Arguments.of(
            new int[] {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, //
            new int[][] {
              {0, 1}, //
              {1, 2}, //
              {1, 4}, //
              {0, 8}, //
              {8, 7}, //
              {9, 10}, //
              {9, 11}, //
              {4, 3}, //
              {6, 5}, //
              {4, 6}, //
              {8, 9}
            },
            new int[][] {
              {1, 8}, // 0
              {2, 4}, // 1
              {}, // 2
              {}, // 3
              {3, 6}, // 4
              {}, // 5
              {5}, // 6
              {}, // 7
              {7, 9}, // 8
              {10, 11}, // 9
              {}, // 10
              {} // 11
            }

            //
            ),
        Arguments.of(
            new int[] {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0}, //
            new int[][] {
              {0, 1}, //
              {0, 2}, //
              {1, 3}, //
              {1, 4}, //
              {2, 5}, //
              {2, 6}, //
              {3, 7}, //
              {4, 8}, //
              {6, 9}, //
              {9, 10}
            },
            new int[][] {
              {1, 2}, // 0
              {3, 4}, // 1
              {5, 6}, // 2
              {7}, // 3
              {8}, // 4
              {}, // 5
              {9}, // 6
              {}, // 7
              {}, // 8
              {10}, // 9
              {}, // 10
            }
            //
            )
        //
        );
  }
}
