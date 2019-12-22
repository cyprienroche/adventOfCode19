package UniversalOrbitMap;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class UMOMain {

  public static void getOrbits(Scanner scanner, PrintStream printStream) {
    List<String> orbits = new LinkedList<>();
    while (scanner.hasNext()) {
      orbits.add(scanner.next());
    }
    String[] strings = orbits.toArray(new String[0]);
    int count = new UMO(strings).getOrbitNumber();
    printStream.println(count);
  }

}
