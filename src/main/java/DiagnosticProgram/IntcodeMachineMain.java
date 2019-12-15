package DiagnosticProgram;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class IntcodeMachineMain {

  public static void execute(Scanner scanner, PrintStream printStream) {
    execute(scanner, printStream, 12, 2);
  }

  public static void execute(Scanner scanner, PrintStream printStream, int noun, int verb) {
    int[] program = Arrays.stream(scanner.next().split(",")).mapToInt(Integer::valueOf).toArray();
    program = new IntcodeMachine(program, noun, verb).execute().getProgram();
    printStream.print(program[0]);
  }

  public static void findVerbAndNounForValue(Scanner scanner, PrintStream printStream, int res) {
    int[] program = Arrays.stream(scanner.next().split(",")).mapToInt(Integer::valueOf).toArray();

    for (int i = 0; i < 99; i++) {
      for (int j = 0; j < 99; j++) {
        int out = new IntcodeMachine(program, i, j).execute().getProgram()[0];
        if (out == res) {
          printStream.print(i * 100 + j);
          return;
        }
      }
    }
  }

}
