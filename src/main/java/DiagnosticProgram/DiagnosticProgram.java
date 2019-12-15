package DiagnosticProgram;

import static DiagnosticProgram.Op.*;

import java.util.Arrays;
import java.util.Scanner;

public class DiagnosticProgram {

  private final int[] program;
  private int PC;
  private Scanner scanner;
  private int code;

  public DiagnosticProgram(final int[] program) {
    this.program = Arrays.copyOf(program, program.length);
    this.PC = 0;
    this.scanner = new Scanner(System.in);
  }

  public int[] getProgram() {
    return program;
  }

  public DiagnosticProgram execute() {
    while (PC + 1 < program.length) {
      int code = program[PC];
      if (code == HALT) {
        return this;
      }
      this.code = code;
      PC += executeOp();
    }
    return this;
  }

  private int executeOp() {
    switch (getOpcode()) {
      case INP -> {
        program[getPositionOfArg(1)] = scanner.nextInt();
        return 2;
      }
      case OUT -> {
        System.out.println(program[getPositionOfArg(1)]);
        return 2;
      }
    }

    int a = program[getPositionOfArg(1)];
    int b = program[getPositionOfArg(2)];
    program[getPositionOfArg(3)] = switch (getOpcode()) {
      case ADD -> a + b;
      case MUL -> a * b;
      default -> throw new IllegalArgumentException(illegalArgMessage(code));
    };
    return 4;
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
