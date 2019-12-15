package DiagnosticProgram;

import static DiagnosticProgram.Op.*;

import java.io.IOException;
import java.util.Arrays;

public class DiagnosticProgram {

  private final int[] program;
  private int PC;

  public DiagnosticProgram(final int[] program) {
    this(program, program[1], program[2]);
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
    while (PC + 3 < program.length) {
      int opcode = program[PC];
      if (opcode == HALT) {
        return this;
      }
      executeOp(opcode);
      PC += 4;
    }
    return this;
  }

  private void executeOp(int opcode) throws IOException {
    int a = program[getPositionOfArg(1)];
    switch (opcode) {
      case INP -> program[a] = System.in.read();
      case OUT -> System.out.println(a);
    }

    int b = program[getPositionOfArg(2)];
    program[getPositionOfArg(3)] = switch (opcode) {
      case ADD -> a + b;
      case MUL -> a * b;
      default -> throw new IllegalArgumentException("Invalid opcode");
    };
  }

  private int getPositionOfArg(int i) {
    return program[PC + i];
  }
}
