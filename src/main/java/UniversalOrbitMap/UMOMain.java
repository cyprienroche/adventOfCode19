package UniversalOrbitMap;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class UMOMain {

  public static void getOrbits(Scanner scanner, PrintStream printStream) {
    String[] strings = generateGraph(scanner);
    int count = new UMO(strings).getOrbitNumber();
    printStream.println(count);
  }

  public static void getOrbitalTransfer(Scanner scanner, PrintStream printStream) {
    String[] strings = generateGraph(scanner);
    int count = new UMO(strings).orbitsYOUToSAN();
    printStream.println(count);
  }

  private static String[] generateGraph(Scanner scanner) {
    List<String> orbits = new LinkedList<>();
    while (scanner.hasNext()) {
      orbits.add(scanner.next());
    }
    return orbits.toArray(new String[0]);
  }

}
