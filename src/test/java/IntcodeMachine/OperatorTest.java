package IntcodeMachine;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import IntCodeMachine.Operator;
import org.junit.Test;

public class OperatorTest {

  @Test
  public void providesAddition() {
    assertThat(Operator.ADD.apply(3,4), is(7));
  }

  @Test
  public void providesMultiplication() {
    assertThat(Operator.MULTIPLY.apply(3,4), is(12));
  }
}