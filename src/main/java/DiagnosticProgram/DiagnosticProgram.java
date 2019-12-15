package DiagnosticProgram;

import static DiagnosticProgram.Op.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class DiagnosticProgram {

  private final int[] program;
  private int PC;
  private InputStream in;
  private OutputStream out;

  public DiagnosticProgram(final int[] program) {
    this(program, program[1], program[2]);
    in = System.in;
    out = System.out;
  }

  public DiagnosticProgram(final int[] program, InputStream in, OutputStream out) {
    this(program, program[1], program[2]);
    this.in = in;
    this.out = out;
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
    switch (opcode) {
      case INP -> {program[getPositionOfArg(1)] = in.read(); return 2;}
      case OUT -> {System.out.println(program[getPositionOfArg(1)]); return 2;}
    }

    int a = program[getPositionOfArg(1)];
    int b = program[getPositionOfArg(2)];
    program[getPositionOfArg(3)] = switch (opcode) {
      case ADD -> a + b;
      case MUL -> a * b;
      default -> throw new IllegalArgumentException("Invalid opcode");
    };
    return 4;
  }

  private int getPositionOfArg(int i) {
    return program[PC + i];
  }
}
