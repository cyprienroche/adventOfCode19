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
    int count = new UMO((String[]) orbits.toArray()).getOrbitNumber();
    printStream.println(count);
  }

}
