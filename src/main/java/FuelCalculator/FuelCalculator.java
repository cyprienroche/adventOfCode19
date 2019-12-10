package FuelCalculator;

import java.io.PrintStream;
import java.util.Scanner;

public class FuelCalculator {

  /**
   * @param scanner input mass of every module
   * @param printStream output sum of fuel required to launch every module
   */
  public static void calculateFuel(Scanner scanner, PrintStream printStream) {
    long sum = 0;
    while (scanner.hasNext()) {
      int mass = scanner.nextInt();
      sum += getFuelRequirement(mass);
    }
    scanner.close();
    printStream.println(sum);
  }

  /**
   * @param mass of a given module
   * @return fuel required to launch a given module
   */
  public static int getFuelRequirement(int mass) {
    return (mass / 3) - 2;
  }

}
