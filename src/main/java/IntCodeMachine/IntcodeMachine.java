package IntCodeMachine;

import java.util.Arrays;

public class IntcodeMachine {

  private final int[] program;
  private int PC;

  public IntcodeMachine(final int[] program) {
    this(program, program[1], program[2]);
  }

  public IntcodeMachine(final int[] program, int noun, int verb) {
    this.program = Arrays.copyOf(program, program.length);
    this.program[1] = noun;
    this.program[2] = verb;
    this.PC = 0;
  }

  public int[] getProgram() {
    return program;
  }

  public IntcodeMachine execute() {
    while (PC + 3 < program.length) {
      int opcode = program[PC];
      if (opcode == Operator.HALT) {
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
    program[getPositionOfArg(3)] = Operator.operators()[opcode - 1].apply(a,b);
  }

  private int getPositionOfArg(int i) {
    return program[PC + i];
  }
}
