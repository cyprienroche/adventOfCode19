package DiagnosticProgram;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class SetUpIOTesting implements TestRule {

  private final ByteArrayInputStream inContent = new ByteArrayInputStream("1".getBytes());
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final InputStream originalIn = System.in;
  private final PrintStream originalOut = System.out;

  @Override
  public Statement apply(Statement base, Description description) {
    return new Statement() {
      @Override
      public void evaluate() throws Throwable {
        //Do something before test
        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));
        try {
          //Execute test
          base.evaluate();
        } finally {
          //Do something after the test
          System.setIn(originalIn);
          System.setOut(originalOut);
        }
      }
    };
  }

  public String getOutContent() {
    return outContent.toString();
  }
}
