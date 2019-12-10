package CrossingWires;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.Set;

public class MinimalStepsIntersectionCalculator {

  public static int minimalCombinedStepsToIntersect(Wire wire1, Wire wire2) {
    Set<Point> pointSet = wire1.getPoints();
    pointSet.retainAll(wire2.getPoints());
    return pointSet
        .stream()
        .map(point -> wire1.getSteps(point) + wire2.getSteps(point))
        .min(Integer::compareTo)
        .orElse(-1);
  }

  public static void execute(Scanner scanner, PrintStream printStream) {
    String[] path1 = scanner.next().split(",");
    String[] path2 = scanner.next().split(",");
    int min = minimalCombinedStepsToIntersect(new Wire(path1), new Wire(path2));
    printStream.print(min);
  }
}
