package CrossingWires;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class DirectionTest {

  @Test
  public void directionsHaveCExpectedComponents() {
    assertThat(Direction.DOWN.getX(), is(0));
    assertThat(Direction.DOWN.getY(), is(-1));

    assertThat(Direction.UP.getX(), is(0));
    assertThat(Direction.UP.getY(), is(1));

    assertThat(Direction.LEFT.getX(), is(-1));
    assertThat(Direction.LEFT.getY(), is(0));

    assertThat(Direction.RIGHT.getX(), is(1));
    assertThat(Direction.RIGHT.getY(), is(0));
  }

  @Test
  public void canGetDirectionGivenFirstCharacter() {
    assertThat(Direction.getDirection('D'), is(Direction.DOWN));
    assertThat(Direction.getDirection('U'), is(Direction.UP));
    assertThat(Direction.getDirection('L'), is(Direction.LEFT));
    assertThat(Direction.getDirection('R'), is(Direction.RIGHT));

  }
}