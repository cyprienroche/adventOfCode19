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
      int next = executeOp();
      PC += next;
    }
    return this;
  }

  private int executeOp() {
    switch (getOpcode()) {
      case INP -> {
        return executeInp();
      }
      case OUT -> {
        return executeOut();
      }
      case ADD -> {
        return executeBOp(getArg(1) + getArg(2));
      }
      case MUL -> {
        return executeBOp(getArg(1) * getArg(2));
      }
      case JPT -> {
        return executeJmp(getArg(1) != 0);
      }
      case JPF -> {
        return executeJmp(getArg(1) == 0);
      }
      case LTH -> {
        return executeCmp(getArg(1) < getArg(2));
      }
      case EQU -> {
        return executeCmp(getArg(1) == getArg(2));
      }
      default -> throw new IllegalArgumentException(illegalArgMessage(code));
    }
  }

  private int executeCmp(boolean cmp) {
    if (cmp) {
      setArg(3, 1);
    } else {
      setArg(3, 0);
    }
    return 4;
  }

  private int executeJmp(boolean jump) {
    if (jump) {
      PC = getArg(2);
      return 0;
    }
    return 3;
  }

  private int executeBOp(int value) {
    setArg(3, value);
    return 4;
  }

  private int executeOut() {
    printStream.println(getArg(1));
    return 2;
  }

  private int executeInp() {
    setArg(1, scanner.nextInt());
    return 2;
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
