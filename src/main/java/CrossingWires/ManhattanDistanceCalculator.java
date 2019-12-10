package CrossingWires;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.Set;

public class ManhattanDistanceCalculator {

  public static int shortestManhattanDistanceToOrigin(Wire wire1, Wire wire2) {
    Set<Point> intersections = wire1.getPoints();
    intersections.retainAll(wire2.getPoints());
    return intersections.stream().map(Point::manhattanDistanceFromOrigin).min(Integer::compareTo).orElse(-1);
  }

  public static void execute(Scanner scanner, PrintStream printStream) {
    String[] path1 = scanner.next().split(",");
    String[] path2 = scanner.next().split(",");
    int min = shortestManhattanDistanceToOrigin(new Wire(path1), new Wire(path2));
    printStream.print(min);
  }

}
