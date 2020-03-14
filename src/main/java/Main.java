
import DiagnosticProgram.DiagnosticMain;
import UniversalOrbitMap.UMOMain;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
    Scanner scanner;
    PrintStream printStream;

    if (args.length < 1 || args[0].equals("System")) {
      scanner = new Scanner(System.in);
    } else {
      File fileIn = new File(args[0]);
      scanner = new Scanner(fileIn);
    }

    if (args.length < 2 || args[1].equals("System")) {
      printStream = new PrintStream(System.out);
    } else {
      File fileOut = new File(args[1]);
      printStream = new PrintStream(fileOut);
    }

//    FuelCalculator.FuelCalculator.calculateFuel(scanner, printStream);
//    IntCodeMachine.IntcodeMachineMain.execute(scanner, printStream);
//    IntCodeMachine.IntcodeMachineMain.findVerbAndNounForValue(scanner, printStream, 19690720);
//    CrossingWires.ManhattanDistanceCalculator.execute(scanner, printStream);
//    CrossingWires.MinimalStepsIntersectionCalculator.execute(scanner, printStream);
//    PasswordPermutation.PasswordPermutation.execute(printStream);
//      DiagnosticMain.execute(scanner, printStream);
//    UMOMain.getOrbits(scanner, printStream);
//    UMOMain.getOrbitalTransfer(scanner, printStream);


    System.out.println("process complete.\nexit...");
  }
// src/main/resources/input.txt src/main/resources/output.txt
}
