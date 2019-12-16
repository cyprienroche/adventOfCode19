package DiagnosticProgram;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.junit.Rule;
import org.junit.Test;

public class DiagnosticProgramTest {

  @Rule
  public final SetUpIOTesting ioTesting = new SetUpIOTesting();

  @Test
  public void haltOnOpcode99() {
    int[] program = {99, 1, 2, 3,
                      4, 5, 6, 7};
    assertThat(new DiagnosticProgram(program).execute().getProgram(), is(program));
  }

  @Test
  public void addOnOpcode1() throws IOException {
    int[] program = {1, 0, 0, 0, 99};
    assertThat(new DiagnosticProgram(program).execute().getProgram(),
        is(new int[]{2, 0, 0, 0, 99}));
  }

  @Test
  public void multiplyOnOpcode2() {
    int[] program1 = {2, 3, 0, 3, 99};
    assertThat(new DiagnosticProgram(program1).execute().getProgram(),
        is(new int[]{2, 3, 0, 6, 99}));

    int[] program2 = {2, 4, 4, 5, 99, 0};
    assertThat(new DiagnosticProgram(program2).execute().getProgram(),
        is(new int[]{2, 4, 4, 5, 99, 9801}));
  }

  @Test
  public void canExecuteComplexPrograms() {
    int[] program1 = {1, 1, 1, 4,
                     99, 5, 6, 0,
                     99};
    assertThat(new DiagnosticProgram(program1).execute().getProgram(),
        is(new int[]{30, 1, 1, 4, 2, 5, 6, 0, 99}));

    int[] program2 = {1, 9, 10, 3,
                      2, 3, 11, 0,
                      99,
                      30, 40, 50};
    assertThat(new DiagnosticProgram(program2).execute().getProgram(),
        is(new int[]{3500, 9, 10, 70, 2, 3, 11, 0, 99, 30, 40, 50}));
  }

  @Test
  public void outputWhateverInputIs2() {
    int[] program = {3, 0, 4, 0, 99};
    new DiagnosticProgram(program).execute();
    assertEquals("1\n", ioTesting.getOutContent());
  }

  @Test
  public void canHandlePositionAndImmediateMode() {
    int[] program1 = {1002, 4, 3, 4, 33};
    int[] program2 = {1101, 100, -1, 4, 0};
    assertThat(new DiagnosticProgram(program1).execute().getProgram(),
        is(new int[]{1002, 4, 3, 4, 99}));
    assertThat(new DiagnosticProgram(program2).execute().getProgram(),
        is(new int[]{1101, 100, -1, 4, 99}));
  }

  @Test
  public void example1() {
    int[] program1 = {3, 0, 1, 0, 6, 6, 1100};
    assertThat(new DiagnosticProgram(program1).execute().getProgram(),
        is(new int[]{1, 0, 1, 0, 6, 6, 1101}));
    int[] program2 = {1101, 1, 238, 0, 99};
    assertThat(new DiagnosticProgram(program2).execute().getProgram(),
        is(new int[]{239, 1, 238, 0, 99}));
  }

  @Test
  public void example2() {
    int[] program0 = {3, 20, 1, 20, 6, 0, 99, 99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 50};
    assertThat(new DiagnosticProgram(program0).execute().getProgram(),
        is(new int[]{100, 20, 1, 20, 6, 0, 99, 99, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}));
  }

  @Test
  public void example3() {
    int[] program3 = {3, 0, 1, 0, 6, 6, 1100, 1, 238, 0, 4, 0, 99};
    new DiagnosticProgram(program3).execute().getProgram();
    assertEquals("239\n", ioTesting.getOutContent());
  }

  @Test
  public void jumpsIfFirstParameterIsNonZero() {
    int[] program1 = {5, 1, 7, 1, 0, 2, 0, 99};
    int[] program2 = {5, 4, 7, 1, 0, 2, 0, 99};
    assertThat(new DiagnosticProgram(program1).execute().getProgram(),
        is(new int[]{5, 1, 7, 1, 0, 2, 0, 99}));
    assertThat(new DiagnosticProgram(program2).execute().getProgram(),
        is(new int[]{12, 4, 7, 1, 0, 2, 0, 99}));
  }

  @Test
  public void jumpsIfFirstParameterIsZero() {
    int[] program1 = {6, 4, 7, 1, 0, 2, 0, 99};
    int[] program2 = {6, 3, 7, 1, 0, 2, 0, 99};
    assertThat(new DiagnosticProgram(program1).execute().getProgram(),
        is(new int[]{6, 4, 7, 1, 0, 2, 0, 99}));
    assertThat(new DiagnosticProgram(program2).execute().getProgram(),
        is(new int[]{13, 3, 7, 1, 0, 2, 0, 99}));
  }

  @Test
  public void storesOneInPosOfThirdParameterIfFirstParameterIsLessThanSecond() {
    int[] program1 = {7, 3, 4, 0, 99};
    int[] program2 = {7, 4, 3, 0, 99};
    assertThat(new DiagnosticProgram(program1).execute().getProgram(),
        is(new int[]{1, 3, 4, 0, 99}));
    assertThat(new DiagnosticProgram(program2).execute().getProgram(),
        is(new int[]{0, 4, 3, 0, 99}));
  }

  @Test
  public void storesOneInPosOfThirdParameterIfFirstParameterIsEqualToSecond() {
    int[] program1 = {8, 3, 3, 0, 99};
    int[] program2 = {8, 3, 4, 0, 99};
    assertThat(new DiagnosticProgram(program1).execute().getProgram(),
        is(new int[]{1, 3, 3, 0, 99}));
    assertThat(new DiagnosticProgram(program2).execute().getProgram(),
        is(new int[]{0, 3, 4, 0, 99}));
  }

  @Test
  public void ifInputEqualEightOutputOneElseOutputZero() {
    int[] program1 = {3, 9, 8, 9, 10, 9, 4, 9, 99, -1, 8};
    new DiagnosticProgram(program1).execute();
    assertEquals("0\n", ioTesting.getOutContent());
  }

  @Test
  public void ifInputLessThanEightOutputOneElseOutputZero() {
    int[] program1 = {3, 9, 7, 9, 10, 9, 4, 9, 99, -1, 8};
    new DiagnosticProgram(program1).execute();
    assertEquals("1\n", ioTesting.getOutContent());
  }


  @Test
  public void ifInputEqualEightOutputOneElseOutputZeroImmediateMode() {
    int[] program1 = {3, 3, 1108, -1, 8, 3, 4, 3, 99};
    new DiagnosticProgram(program1).execute();
    assertEquals("0\n", ioTesting.getOutContent());
  }

  @Test
  public void ifInputLessThanEightOutputOneElseOutputZeroImmediateMode() {
    int[] program1 = {3, 3, 1107, -1, 8, 3, 4, 3, 99};
    new DiagnosticProgram(program1).execute();
    assertEquals("1\n", ioTesting.getOutContent());
  }

  @Test
  public void example4() {
    int[] program1 = {3, 21, 1008, 21, 8, 20, 1005, 20, 22, 107, 8, 21, 20, 1006, 20, 31,
        1106, 0, 36, 98, 0, 0, 1002, 21, 125, 20, 4, 20, 1105, 1, 46, 104,
        999, 1105, 1, 46, 1101, 1000, 1, 20, 4, 20, 1105, 1, 46, 98, 99};
    new DiagnosticProgram(program1).execute();
    assertEquals("999\n", ioTesting.getOutContent());
  }
}