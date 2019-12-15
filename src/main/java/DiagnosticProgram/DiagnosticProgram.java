package DiagnosticProgram;

import java.util.Arrays;

public class DiagnosticProgram {

  private static final int HALT = 99;
  private static final int ADD = 1;
  private static final int MUL = 2;

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

  public DiagnosticProgram execute() {
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

  private void executeOp(int opcode) {
    int a = program[getPositionOfArg(1)];
    int b = program[getPositionOfArg(2)];
    program[getPositionOfArg(3)] = switch (opcode) {
      case ADD -> a + b;
      case MUL -> a * b;
      default -> 0;
    };
  }

  private int getPositionOfArg(int i) {
    return program[PC + i];
  }
}
