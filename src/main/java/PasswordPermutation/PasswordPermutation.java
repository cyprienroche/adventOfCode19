package PasswordPermutation;

import java.io.PrintStream;
import java.util.function.Function;
import java.util.stream.IntStream;

public class PasswordPermutation {

  //input: 356261-846303

  public static boolean isValid(int p) {
    return length(p) == 6 && isSorted(p) && hasTwoEqualAdjacentDigits(p);
  }

  public static boolean isValidWithExactlyTwoDigits(int p) {
    return length(p) == 6 && isSorted(p) && hasExactlyTwoEqualAdjacentDigits(p);
  }

  private static int length(int p) {
    int count = 0;
    while (p > 0) {
      count++;
      p/= 10;
    }
    return count;
  }

  private static boolean hasTwoEqualAdjacentDigits(int num) {
    while (num > 10) {
      if (num % 10 == (num / 10) % 10) {
        return true;
      }
      num /= 10;
    }
    return false;
  }

  private static boolean hasExactlyTwoEqualAdjacentDigits(int num) {
    int prev = -1;
    while (num > 10) {
      if (num % 10 == (num / 10) % 10) {
        if (num % 10 != prev && num % 10 != (num / 100) % 10) {
          return true;
        }
        prev = num % 10;
      }
      num /= 10;
    }
    return false;
  }

  private static boolean isSorted(int num) {
    while (num > 10) {
      if (num % 10 < (num / 10) % 10) {
        return false;
      }
      num /= 10;
    }
    return true;
  }

  public static long differentPasswordsWithinRange(int low, int high) {
    return IntStream.range(low, high).filter(x -> isValid(x)).count();
  }

  public static long differentPasswordsWithinRangeWithExactlyTwoDigits(int low, int high) {
    return IntStream.range(low, high).filter(x -> isValidWithExactlyTwoDigits(x)).count();
  }

  public static void execute(PrintStream printStream) {
    printStream.println("part1: " + differentPasswordsWithinRange(356261,846303));
    printStream.println("part2: " + differentPasswordsWithinRangeWithExactlyTwoDigits(356261,846303));
  }
}
