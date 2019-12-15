package DiagnosticProgram;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class IntcodeMachineTest {

  @Test
  public void haltOnOpcode99() {
    int[] program = {99,1,2,3,
                      4,5,6,7};
    assertThat(new IntcodeMachine(program).execute().getProgram(), is(program));
  }

  @Test
  public void addOnOpcode1() {
    int[] program = {1,0,0,0,
                     99};
    assertThat(new IntcodeMachine(program).execute().getProgram(), is(new int[]{2,0,0,0,99}));
  }

  @Test
  public void multiplyOnOpcode2() {
    int[] program1 = {2,3,0,3,
                     99};
    assertThat(new IntcodeMachine(program1).execute().getProgram(), is(new int[]{2,3,0,6,99}));

    int[] program2 = {2,4,4,5,
                      99,0};
    assertThat(new IntcodeMachine(program2).execute().getProgram(), is(new int[]{2,4,4,5,99,9801}));
  }

  @Test
  public void canExecuteComplexPrograms() {
    int[] program1 = { 1,1,1,4,
                      99,5,6,0,
                      99};
    assertThat(new IntcodeMachine(program1).execute().getProgram(), is(new int[]{30,1,1,4,2,5,6,0,99}));

    int[] program2 = {1,9,10,3,
                      2,3,11,0,
                      99,
                      30,40,50};
    assertThat(new IntcodeMachine(program2).execute().getProgram(), is(new int[]{3500,9,10,70,
                                                                       2,3,11,0,
                                                                       99,
                                                                       30,40,50}));
  }
}