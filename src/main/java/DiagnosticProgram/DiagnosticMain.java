package DiagnosticProgram;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class DiagnosticMain {

  public static void execute(Scanner scanner, PrintStream printStream) {
    int[] program = Arrays.stream(scanner.next().split(",")).mapToInt(Integer::valueOf).toArray();
    program = new DiagnosticProgram(program).execute().getProgram();
    printStream.print(program[0]);
  }
}
