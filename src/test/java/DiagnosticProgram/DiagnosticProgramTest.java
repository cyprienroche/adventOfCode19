package DiagnosticProgram;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiagnosticProgramTest {

  private final InputStream in = new ByteArrayInputStream(new byte[]{1});
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  public void haltOnOpcode99() throws IOException {
    int[] program = {99,1,2,3,
                      4,5,6,7};
    assertThat(new DiagnosticProgram(program).execute().getProgram(), is(program));
  }

  @Test
  public void addOnOpcode1() throws IOException {
    int[] program = {1,0,0,0,
                     99};
    assertThat(new DiagnosticProgram(program).execute().getProgram(), is(new int[]{2,0,0,0,99}));
  }

  @Test
  public void multiplyOnOpcode2() throws IOException {
    int[] program1 = {2,3,0,3,
                     99};
    assertThat(new DiagnosticProgram(program1).execute().getProgram(), is(new int[]{2,3,0,6,99}));

    int[] program2 = {2,4,4,5,
                      99,0};
    assertThat(new DiagnosticProgram(program2).execute().getProgram(), is(new int[]{2,4,4,5,99,9801}));
  }

  @Test
  public void canExecuteComplexPrograms() throws IOException {
    int[] program1 = { 1,1,1,4,
                      99,5,6,0,
                      99};
    assertThat(new DiagnosticProgram(program1).execute().getProgram(), is(new int[]{30,1,1,4,2,5,6,0,99}));

    int[] program2 = {1,9,10,3,
                      2,3,11,0,
                      99,
                      30,40,50};
    assertThat(new DiagnosticProgram(program2).execute().getProgram(), is(new int[]{3500,9,10,70,
                                                                       2,3,11,0,
                                                                       99,
                                                                       30,40,50}));
  }

  @Test
  public void outputWhateverInputIs2() throws IOException {
    int[] program = {3,0,4,0,99};
    new DiagnosticProgram(program, in).execute();
    assertEquals("1\n", outContent.toString());
  }

  @Test
  public void canHandlePositionAndImmediateMode() throws IOException {
    int[] program1 = {1002,4,3,4,33};
    int[] program2 = {1101,100,-1,4,0};
    assertThat(new DiagnosticProgram(program1).execute().getProgram(), is(new int[]{1002,4,3,4,99}));
    assertThat(new DiagnosticProgram(program2).execute().getProgram(), is(new int[]{1101,100,-1,4,99}));
  }

  @Test
  public void example1() throws IOException {
    //3,225,1,225,6,6,1100,1,238,225,104,0,1102,67,92,225,1101,14,84,225,1002,217,69,224,101,-5175,224
    int[] program1 = {3,0,1,0,6,6,1100};
    assertThat(new DiagnosticProgram(program1, in).execute().getProgram(), is(new int[]{1,0,1,0,6,6,1101}));
    int[] program2 = {1101,1,238,0,99};
    assertThat(new DiagnosticProgram(program2, in).execute().getProgram(), is(new int[]{239,1,238,0,99}));
  }

  @Test
  public void example2() throws IOException {
    int[] program3 = {3,0,1,0,6,6,1100,1,238,0,4,0,99};
    new DiagnosticProgram(program3, in).execute().getProgram();
    assertEquals("239\n", outContent.toString());
  }
}