package DiagnosticProgram;

import static DiagnosticProgram.Op.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class DiagnosticProgram {

  private final int[] program;
  private int PC;
  private Scanner scanner;

  public DiagnosticProgram(final int[] program) {
    this(program, program[1], program[2]);
    scanner = new Scanner(System.in);
  }

  public DiagnosticProgram(final int[] program, InputStream in) {
    this(program, program[1], program[2]);
    this.scanner = new Scanner(in);
  }

  public DiagnosticProgram(final int[] program, int noun, int verb) {
    this.program = Arrays.copyOf(program, program.length);
    this.PC = 0;
    this.program[1] = noun;
    this.program[2] = verb;
  }

  public int[] getProgram() {
    return program;
  }

  public DiagnosticProgram execute() throws IOException {
    while (PC + 1 < program.length) {
      int opcode = program[PC];
      if (opcode == HALT) {
        return this;
      }
      PC += executeOp(opcode);;
    }
    return this;
  }

  private int executeOp(int opcode) throws IOException {
    switch (opcode % 100) {
      case INP -> {program[getPositionOfArg(1, opcode / 100)] = scanner.nextInt(); return 2;}
      case OUT -> {System.out.println(program[getPositionOfArg(1, opcode / 100)]); return 2;}
    }

    int a = program[getPositionOfArg(1, opcode / 100)];
    int b = program[getPositionOfArg(2, opcode / 1000)];
    program[getPositionOfArg(3, opcode / 10000)] = switch (opcode % 100) {
      case ADD -> a + b;
      case MUL -> a * b;
      default -> throw new IllegalArgumentException("Invalid opcode. opcode: " + opcode + " at position " + getPositionOfArg(0, opcode/ 100));
    };
    return 4;
  }

  private int getPositionOfArg(int i, int code) {
    if (code % 10 == 1) {
      return PC + i;
    }
    return program[PC + i];
  }
}
