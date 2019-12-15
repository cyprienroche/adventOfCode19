package DiagnosticProgram;

import static DiagnosticProgram.Op.*;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;

public class DiagnosticProgram {

  private final int[] program;
  private int PC;
  private int code;
  private Scanner scanner = new Scanner(System.in);
  private PrintStream printStream = System.out;

  public DiagnosticProgram(final int[] program) {
    this.program = Arrays.copyOf(program, program.length);
    this.PC = 0;
  }

  public int[] getProgram() {
    return program;
  }

  public DiagnosticProgram execute() {
    while (PC + 1 < program.length) {
      int opcode = program[PC];
      if (opcode == HALT) {
        return this;
      }
      code = opcode;
      PC += executeOp();
    }
    return this;
  }

  private int executeOp() {
    switch (getOpcode()) {
      case INP -> {
        setArg(1, scanner.nextInt());
        return 2;
      }
      case OUT -> {
        printStream.println(getArg(1));
        return 2;
      }
      case ADD -> {
        setArg(3, getArg(1) + getArg(2));
        return 4;
      }
      case MUL -> {
        setArg(3, getArg(1) * getArg(2));
        return 4;
      }
      default -> throw new IllegalArgumentException(illegalArgMessage(code));
    }
  }

  private void setArg(int i, int value) {
    program[getPositionOfArg(i)] = value;
  }

  private int getArg(int i) {
    return program[getPositionOfArg(i)];
  }

  private int getOpcode() {
    return code % 100;
  }

  private String illegalArgMessage(int opcode) {
    return String.format("Invalid opcode. opcode: %d at PC %d", opcode, PC);
  }

  private int getPositionOfArg(int i) {
    int argImmDigit = code / (int) Math.pow(10, i + 1);
    return isImmediate(argImmDigit) ? PC + i : program[PC + i];
  }

  private boolean isImmediate(int code) {
    return code % 10 == 1;
  }
}
